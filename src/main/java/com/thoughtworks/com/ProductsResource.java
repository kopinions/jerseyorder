package com.thoughtworks.com;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/products")
public class ProductsResource {

    @Inject
    ProductCatalog catalog;

    @Path("{id}/prices")
    public PriceResource getPrices() {
        return new PriceResource();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductsRefJson getProductList() {
        return new ProductsRefJson(catalog.getProductList());
    }
}
