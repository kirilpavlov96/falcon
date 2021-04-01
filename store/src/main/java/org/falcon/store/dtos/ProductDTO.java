package org.falcon.store.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * Data transfer object for product.
 */
public class ProductDTO {
    @NotEmpty
    private String name;
    @Positive
    private int quantity;
    private int outOfStock;

    /**
     * Default constructor.
     */
    public ProductDTO() {
    }

    /**
     * Constructor using fields.
     *
     * @param name       product name.
     * @param quantity   product quantity.
     * @param outOfStock product outOfStock.
     */
    public ProductDTO(String name, int quantity, int outOfStock) {
        this.name = name;
        this.quantity = quantity;
        this.outOfStock = outOfStock;
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
