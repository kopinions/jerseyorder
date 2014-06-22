package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class ProductsRefJson  {

    private Product product;
    private UriInfo uriInfo;


    public ProductsRefJson(Product product, UriInfo uriInfo) {
        this.product = product;
        this.uriInfo = uriInfo;
    }

    public ProductsRefJson() {
    }

    public URI getUri() {
        return uriInfo.getAbsolutePathBuilder().path(String.valueOf(product.getId())).build();
    }

    public String getName() {
        return product.getName();
    }

    public String getLocation() {
        return product.getLocation();
    }

    public PriceRefJson getPrice() {
        return new PriceRefJson(product.getCurrentPrice(), uriInfo.getAbsolutePathBuilder().path(String.valueOf(product.getId())));
    }
}
