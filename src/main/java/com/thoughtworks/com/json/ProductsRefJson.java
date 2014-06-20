package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Product;

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
