package org.falcon.store.models;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

/**
 * Mongo entity for product.
 */
@MongoEntity(collection = "products")
public class ProductEntity {
    private ObjectId id;
    private String name;
    private int quantity;
    private int outOfStock;

    /**
     * Default constructor.
     */
    public ProductEntity() {
    }

    /**
     * Constructor using fields.
     *
     * @param name       product name.
     * @param quantity   product quantity.
     * @param outOfStock product outOfStock counter.
     */
    public ProductEntity(String name, int quantity, int outOfStock) {
        this.name = name;
        this.quantity = quantity;
        this.outOfStock = outOfStock;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(int outOfStock) {
        this.outOfStock = outOfStock;
    }
}
