package org.falcon.shop.dtos;

import org.falcon.shop.models.OrderStatusEnum;
import org.falcon.shop.models.ProductRequest;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Data transfer object for order.
 */
public class OrderDTO {
    private String uuid;
    private OrderStatusEnum status;
    @NotEmpty
    private List<ProductRequest> productRequests;

    /**
     * Default constructor.
     */
    public OrderDTO() {
    }

    /**
     * Constructor using fields.
     *
     * @param uuid            order uuid.
     * @param status          order status.
     * @param productRequests order product requests.
     */
    public OrderDTO(String uuid, OrderStatusEnum status, List<ProductRequest> productRequests) {
        this.uuid = uuid;
        this.status = status;
        this.productRequests = productRequests;
    }

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

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }
}
