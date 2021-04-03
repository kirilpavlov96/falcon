package org.falcon.store.services;

import org.falcon.store.dtos.ProductDTO;
import org.falcon.store.exceptions.InvalidParameterException;
import org.falcon.store.models.ProductEntity;
import org.falcon.store.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Service for product related operations.
 */
@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    /**
     * Finds all products that are out of scope.
     *
     * @return List of {@link ProductDTO}
     */
    public List<ProductEntity> getOutOfStock() {
        return productRepository.findAllOutOfStock();
    }

    /**
     * Create new product or update the quantity of already existing one.
     *
     * @param products the product to create or update.
     */
    public synchronized void createOrUpdateProducts(List<ProductDTO> products) throws InterruptedException {
        if (products == null) {
            throw new InvalidParameterException();
        }
        List<ProductEntity> toPersist = new LinkedList<>();
        products.forEach(productDTO -> {
            ProductEntity productEntity = productRepository.findByName(productDTO.getName());
            if (productEntity == null) {
                productEntity = new ProductEntity(productDTO.getName(), productDTO.getQuantity(), 0);
            } else {
                productEntity.setQuantity(productEntity.getQuantity() + productDTO.getQuantity());
                int outOfStock = Math.max(0, productEntity.getOutOfStock() - productDTO.getQuantity());
                productEntity.setOutOfStock(outOfStock);
            }
            toPersist.add(productEntity);
        });
        if (!toPersist.isEmpty()) {
            productRepository.persistOrUpdate(toPersist);
        }
    }
}
