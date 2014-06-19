package com.thoughtworks.com;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class ProductsRefJson {

    private List<Product> productList;


    public ProductsRefJson(List<Product> productList) {
        this.productList = productList;
    }


}
