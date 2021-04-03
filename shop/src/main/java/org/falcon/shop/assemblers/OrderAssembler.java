package org.falcon.shop.assemblers;

import org.falcon.shop.dtos.OrderDTO;
import org.falcon.shop.models.OrderEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts OrderEntity to DTO.
 */
@ApplicationScoped
public class OrderAssembler {

    /**
     * Converts {@link OrderEntity} to {@link OrderDTO}
     *
     * @param entity {@link OrderEntity}
     * @return {@link OrderDTO}
     */
    public OrderDTO assemble(OrderEntity entity) {
        return new OrderDTO(entity.getUuid(), entity.getStatus(), entity.getProductRequests());
    }

    /**
     * Converts {@link Collection} of {@link OrderEntity} to {@link List} of {@link OrderDTO}
     *
     * @param entities {@link Collection} of {@link OrderEntity}
     * @return {@link List} of {@link OrderDTO}
     */
    public List<OrderDTO> assemble(Collection<OrderEntity> entities) {
        return entities.stream().map(this::assemble).collect(Collectors.toList());
    }
}
