package org.falcon.shop.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.falcon.shop.models.OrderEntity;

import javax.enterprise.context.ApplicationScoped;

/**
 * Panache mongo repository for order entity.
 */
@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {
    /**
     * Finds order by uuid.
     *
     * @param uuid the uuid
     * @return {@link OrderEntity}
     */
    public OrderEntity findByUuid(String uuid) {
        return find("uuid", uuid).firstResult();
    }

    /**
     * Finds order by product request uuid.
     *
     * @param uuid the uuid of the product request
     * @return {@link OrderEntity}
     */
    public OrderEntity findByRequestUuid(String uuid) {
        return find("{ 'productRequests.uuid': ?1 }", uuid).firstResult();
    }

    /**
     * Updates the completed flag to true of a product request.
     *
     * @param uuid the uuid of the product request
     */
    public void completeProductRequest(String uuid) {
        update("{$set: { 'productRequests.$.completed': true }}").where("{ 'productRequests.uuid': ?1 }", uuid);
    }
}
