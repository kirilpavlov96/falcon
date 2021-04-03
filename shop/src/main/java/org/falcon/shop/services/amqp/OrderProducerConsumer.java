package org.falcon.shop.services.amqp;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.falcon.shop.dtos.OrderDTO;
import org.falcon.shop.models.OrderEntity;
import org.falcon.shop.models.OrderStatusEnum;
import org.falcon.shop.models.ProductRequest;
import org.falcon.shop.repositories.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * AMQP producer/consumer for order.
 */
@ApplicationScoped
public class OrderProducerConsumer {

    @Inject
    @Channel("orders-out")
    Emitter<ProductRequest> emitter;

    @Inject
    OrderRepository orderRepository;

    /**
     * Sends the order to the emitter.
     *
     * @param order the order
     */
    public void send(OrderDTO order) {
        order.getProductRequests().forEach(emitter::send);
    }

    @Incoming("orders-in")
    public Uni<Void> receive(ProductRequest productRequest) {
        if (productRequest != null) {
            orderRepository.completeProductRequest(productRequest.getUuid());
            OrderEntity orderEntity = orderRepository.findByRequestUuid(productRequest.getUuid());
            if (orderEntity != null) {
                boolean isCompleted = orderEntity.getProductRequests().stream().allMatch(ProductRequest::getCompleted);
                if (isCompleted) {
                    //TODO SOCKET will be applied here
                    orderEntity.setStatus(OrderStatusEnum.COMPLETED);
                    orderRepository.update(orderEntity);
                }
            }
        }
        return Uni.createFrom().voidItem();
    }
}
