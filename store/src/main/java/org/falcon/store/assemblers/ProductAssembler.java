package org.falcon.store.assemblers;

import org.falcon.store.dtos.ProductDTO;
import org.falcon.store.models.ProductEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts ProductEntity to DTO.
 */
@ApplicationScoped
public class ProductAssembler {

    /**
     * Converts {@link ProductEntity} to {@link ProductDTO}
     *
     * @param entity {@link ProductEntity}
     * @return {@link ProductDTO}
     */
    public ProductDTO assemble(ProductEntity entity) {
        return new ProductDTO(entity.getName(), entity.getQuantity(), entity.getOutOfStock());
    }

    /**
     * Converts {@link Collection} of {@link ProductEntity} to {@link List} of {@link ProductDTO}
     *
     * @param entities {@link Collection} of {@link ProductEntity}
     * @return {@link List} of {@link ProductDTO}
     */
    public List<ProductDTO> assemble(Collection<ProductEntity> entities) {
        return entities.stream().map(this::assemble).collect(Collectors.toList());
    }
}
