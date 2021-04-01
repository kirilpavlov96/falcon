package org.falcon.shop.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * Class containing name and quantity of the requested product item.
 */
public class ProductRequest {
    @NotEmpty
    private String name;
    @Positive
    private int quantity;

    /**
     * Default constructor.
     */
    public ProductRequest() {
    }

    /**
     * Constructor using fields.
     *
     * @param name     the name of the requested product
     * @param quantity the quantity of the requested product
     */
    public ProductRequest(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
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
