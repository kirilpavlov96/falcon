package org.falcon.store.dtos;

import io.quarkus.mongodb.panache.MongoEntity;

import java.util.List;

/**
 * DTO for order.
 */
@MongoEntity(collection = "orders")
public class OrderDTO {
    private String uuid;
    private OrderStatusEnum status;
    private List<ProductRequestDTO> productRequests;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public List<ProductRequestDTO> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequestDTO> productRequests) {
        this.productRequests = productRequests;
    }
}
