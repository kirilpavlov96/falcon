package org.falcon.store.resources;

import org.falcon.store.assemblers.ProductAssembler;
import org.falcon.store.dtos.ProductDTO;
import org.falcon.store.models.ProductEntity;
import org.falcon.store.services.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * REST controller for product.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @Inject
    ProductAssembler productAssembler;

    /**
     * Finds all products that are out of scope.
     *
     * @return JSON with all out of stock products.
     */
    @GET
    public List<ProductDTO> getOutOfStock() {
        return productAssembler.assemble(productService.getOutOfStock());
    }

    /**
     * Create new product or update the quantity of already existing one.
     *
     * @param products the product to create or update.
     */
    @POST
    public void createOrUpdateProducts(List<ProductDTO> products) {
        productService.createOrUpdateProducts(products);
    }
}