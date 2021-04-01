package org.falcon.shop.resources;

import org.falcon.shop.dtos.OrderDTO;
import org.falcon.shop.services.OrderService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST controller for orders.
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return {@link Response}
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder(@Valid OrderDTO order) {
        orderService.createOrder(order);
        return Response.ok().build();
    }
}