package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IPriceRepository;
import com.thoughtworks.com.domain.IProductsCatalog;
import com.thoughtworks.com.json.ProductJson;
import com.thoughtworks.com.json.ProductsRefJson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {

    @Inject
    IProductsCatalog catalog;

    @Path("{id}/prices")
    public PricesResource getPrices(@PathParam("id") int productId, @Context UriInfo uriInfo, @Context IPriceRepository priceRepo) {
        return new PricesResource(catalog.find(productId), uriInfo, priceRepo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsRefJson> getProductList() {
        return catalog.getProductList().stream().map(ProductsRefJson::new).collect(Collectors.toList());
    }


    @GET
    @Path("{id}")
    public ProductJson getProduct(@PathParam("id") int productId, @Context UriInfo uriInfo) {
        return new ProductJson(catalog.find(productId), uriInfo);
    }
}
