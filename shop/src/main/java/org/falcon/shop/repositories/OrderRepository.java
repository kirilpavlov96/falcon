package org.falcon.shop.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.falcon.shop.models.OrderEntity;

import javax.enterprise.context.ApplicationScoped;

/**
 * Panache mongo repository for order entity.
 */
@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {
}
