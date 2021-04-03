package org.falcon.shop.services;

import org.falcon.shop.assemblers.OrderAssembler;
import org.falcon.shop.dtos.OrderDTO;
import org.falcon.shop.exceptions.InvalidParameterException;
import org.falcon.shop.models.OrderEntity;
import org.falcon.shop.models.OrderStatusEnum;
import org.falcon.shop.repositories.OrderRepository;
import org.falcon.shop.services.amqp.OrderProducerConsumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Service for product related operations.
 */
@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderProducerConsumer orderProducerConsumer;

    @Inject
    OrderAssembler orderAssembler;

    /**
     * Create new order.
     *
     * @param orderDTO the order to create.
     */
    public void createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new InvalidParameterException();
        }
        OrderEntity orderEntity = new OrderEntity(OrderStatusEnum.PROCESSED, orderDTO.getProductRequests());
        orderRepository.persist(orderEntity);
        orderProducerConsumer.send(orderAssembler.assemble(orderEntity));
    }
}
