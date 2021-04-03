package org.falcon.store.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.falcon.store.models.ProductEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Panache mongo repository for product entity.
 */
@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<ProductEntity> {
    /**
     * Finds product by name.
     *
     * @param name the name
     * @return {@link ProductEntity}
     */
    @Transactional
    public ProductEntity findByName(String name) {
        return find("name", name).firstResult();
    }

    /**
     * Finds all out of stock products.
     *
     * @return List of {@link ProductEntity}
     */
    public List<ProductEntity> findAllOutOfStock() {
        return find("outOfStock: { $gt: 0 }").list();
    }

    /**
     * Decrements the quantity of the product.
     *
     * @param entity      the product.
     * @param toDecrement the number to decrement with
     * @return long
     */
    public long decrQuantityOfProduct(ProductEntity entity, int toDecrement) {
        return update("{ $inc: { 'quantity': ?1 } }", -toDecrement)
                .where("{ 'name': ?1, 'quantity': { $gte: ?2} }", entity.getName(), toDecrement);
    }

    /**
     * Increments the outOfStock of the product.
     *
     * @param entity      the product.
     * @param toIncrement the number to increment with
     */
    public void incOutOfStockOfProduct(ProductEntity entity, int toIncrement) {
        update("{ $inc: { 'outOfStock': ?1 } }", toIncrement).where("{ 'name': ?1 }", entity.getName());
    }
}
