package com.thoughtworks.com.domain;

import java.util.List;

public interface IProductsCatalog {
    public Product create(String name);

    public List<Product> getProductList();

    public Product find(long id);
}
