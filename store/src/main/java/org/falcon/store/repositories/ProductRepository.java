package org.falcon.store.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.falcon.store.models.ProductEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Panache mongo repoditory for product entity.
 */
@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<ProductEntity> {
    /**
     * Finds product by name.
     *
     * @param name the name
     * @return {@link ProductEntity}
     */
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
}
