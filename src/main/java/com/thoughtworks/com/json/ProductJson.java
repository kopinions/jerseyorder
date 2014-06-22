package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class ProductJson{
    private Product product;
    private UriInfo uriInfo;

    public ProductJson() {

    }

    public ProductJson(Product product, UriInfo uriInfo) {
        this.product = product;
        this.uriInfo = uriInfo;
    }

    public URI getUri() {
        return uriInfo.getAbsolutePathBuilder().build();
    }


    public String getName() {
        return product.getName();
    }


    public PriceRefJson getPrice() {
        return new PriceRefJson(product.getCurrentPrice(), uriInfo.getRequestUriBuilder().path(String.valueOf(product.getId())));
    }

    public String getLocation() {
        return product.getLocation();
    }
}
