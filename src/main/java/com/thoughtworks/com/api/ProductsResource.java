package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IProductsCatalog;
import com.thoughtworks.com.json.ProductsRefJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {

    @Inject
    IProductsCatalog catalog;
//
//    @Path("{id}/prices")
//    public PriceResource getPrices() {
//        return new PriceResource();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsRefJson> getProductList() {
        return catalog.getProductList().stream().map(ProductsRefJson::new).collect(Collectors.toList());
    }
}
