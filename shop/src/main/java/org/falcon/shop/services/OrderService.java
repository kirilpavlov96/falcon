package org.falcon.shop.services;

import org.falcon.shop.dtos.OrderDTO;
import org.falcon.shop.exceptions.InvalidParameterException;
import org.falcon.shop.models.OrderEntity;
import org.falcon.shop.models.OrderStatusEnum;
import org.falcon.shop.repositories.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Service for product related operations.
 */
@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    /**
     * Create new order
     *
     * @param orderDTO the order to create.
     */
    public void createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new InvalidParameterException();
        }
        orderRepository.persist(new OrderEntity(OrderStatusEnum.PROCESSED, orderDTO.getProductRequests()));
    }
}
