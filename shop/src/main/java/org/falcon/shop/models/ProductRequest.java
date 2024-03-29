package org.falcon.shop.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * Class containing name and quantity of the requested product item.
 */
public class ProductRequest {
    private String uuid;
    private boolean completed;
    @NotEmpty
    private String name;
    @Positive
    private int quantity;

    /**
     * Default constructor.
     */
    public ProductRequest() {
        this.uuid = UUID.randomUUID().toString();
        this.completed = false;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
}
