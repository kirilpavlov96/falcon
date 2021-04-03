package org.falcon.store.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * Class containing name and quantity of the requested product item.
 */
public class ProductRequestDTO {
    private String uuid;
    private boolean completed;
    @NotEmpty
    private String name;
    @Positive
    private int quantity;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        completed = completed;
    }

    public boolean isCompleted() {
        return completed;
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
