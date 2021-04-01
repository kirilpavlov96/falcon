package org.falcon.shop.models;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Mongo entity for order.
 */
@MongoEntity(collection = "orders")
public class OrderEntity {
    private ObjectId id;
    private OrderStatusEnum status;
    private List<ProductRequest> productRequests;

    /**
     * Default constructor.
     */
    public OrderEntity() {
    }

    /**
     * Constructor using fields.
     *
     * @param productRequests {@link List} of {@link ProductRequest}
     */
    public OrderEntity(OrderStatusEnum status, List<ProductRequest> productRequests) {
        this.status = OrderStatusEnum.PROCESSED;
        this.productRequests = productRequests;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }
}
