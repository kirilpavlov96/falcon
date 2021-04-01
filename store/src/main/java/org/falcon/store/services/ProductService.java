package org.falcon.store.services;

import org.falcon.store.dtos.ProductDTO;
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
    public void createOrUpdateProducts(List<ProductDTO> products) {
        List<ProductEntity> toPersist = new LinkedList<>();
        if (products != null) {
            products.forEach(productDTO -> {
                ProductEntity productEntity = productRepository.findByName(productDTO.getName());
                if (productEntity == null) {
                    productEntity = new ProductEntity(productDTO.getName(), productDTO.getQuantity(), 0);
                } else {
                    productEntity.setQuantity(productEntity.getQuantity() + productDTO.getQuantity());
                }
                toPersist.add(productEntity);
            });
        }
        if (!toPersist.isEmpty()) {
            productRepository.persistOrUpdate(toPersist);
        }
    }
}
