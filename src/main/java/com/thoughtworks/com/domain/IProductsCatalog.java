package com.thoughtworks.com.domain;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProductsCatalog {
    public Product create(String name);

    public List<Product> getProductList();

    public Product find(int id);

    int createProductPrice(@Param("product") Product product, @Param("price") Price price);

    List<Price> getProductPrices(@Param("product") Product product);
}
