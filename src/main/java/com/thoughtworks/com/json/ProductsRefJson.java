package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Product;

public class ProductsRefJson  {

    private Product product;


    public ProductsRefJson(Product product) {
        this.product = product;
    }

    public ProductsRefJson() {
    }

    public int getId() {
        return product.getId();
    }
}
