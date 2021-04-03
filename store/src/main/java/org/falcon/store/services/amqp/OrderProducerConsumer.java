package org.falcon.store.services.amqp;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.impl.ConcurrentHashSet;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.falcon.store.dtos.ProductRequestDTO;
import org.falcon.store.exceptions.InvalidParameterException;
import org.falcon.store.models.ProductEntity;
import org.falcon.store.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * AMQP producer/consumer for order.
 */
@ApplicationScoped
public class OrderProducerConsumer {

    private static final long AWAIT_MILLIS = 5000;

    private final ConcurrentHashSet<String> retriedProductRequests = new ConcurrentHashSet<>();

    @Inject
    ProductRepository productRepository;

    /**
     * Processes incoming product request from the shop asynchronously and send them back to the producer or to DLQ
     * if it cannot be processed.
     *
     * @param productRequest the request to process
     * @return {@link Uni}
     */
    @Incoming("orders-in")
    @Outgoing("orders-out")
    public Uni<ProductRequestDTO> processOrderRequest(ProductRequestDTO productRequest) {
        try {
            processOrderRequestInternal(productRequest);
        } catch (Exception e) {
            return Uni.createFrom().failure(e);
        }
        retriedProductRequests.remove(productRequest.getUuid());
        return Uni.createFrom().item(productRequest);
    }


    /**
     * Processes incoming product request from DLQ synchronously, sleep for the configured period and sends them back
     * for processing.
     *
     * @param productRequest the request to process
     * @return ProductRequestDTO
     */
    @Incoming("dlq-in")
    @Outgoing("dlq-out")
    @Blocking
    public ProductRequestDTO processDLQ(ProductRequestDTO productRequest) throws InterruptedException {
        //TODO Configure Message Redelivery and DLQ Handling for ActiveMQ instead of the thread sleep
        Thread.sleep(AWAIT_MILLIS);
        return productRequest;
    }

    private void processOrderRequestInternal(ProductRequestDTO productRequest) {
        ProductEntity productEntity = productRepository.findByName(productRequest.getName());
        if (productEntity == null) {
            throw new InvalidParameterException("Product does not exist.");
        }
        long updatedDocumentsCount = productRepository.decrQuantityOfProduct(productEntity,
                productRequest.getQuantity());
        if (updatedDocumentsCount == 0) {
            if (!retriedProductRequests.contains(productRequest.getUuid())) {
                productRepository.incOutOfStockOfProduct(productEntity, productRequest.getQuantity());
                retriedProductRequests.add(productRequest.getUuid());
            }
            throw new InvalidParameterException("Product quantity not enough.");
        }
    }

}
