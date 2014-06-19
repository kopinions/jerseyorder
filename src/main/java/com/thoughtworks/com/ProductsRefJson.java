package com.thoughtworks.com;

import javax.ws.rs.ext.Provider;

@Provider
public class ProductsRefJson  {

    private Product product;


    public ProductsRefJson(Product product) {
        this.product = product;
    }

    public Long getId() {
        return product.getId();
    }
}
