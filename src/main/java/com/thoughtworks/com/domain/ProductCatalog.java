package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductCatalog implements IProductsCatalog {
    private final SqlSession session;

    public ProductCatalog(SqlSession session) {
        this.session = session;
    }

    public Product create(String name) {
        return null;
    }

    public List<Product> getProductList() {
        return session.selectList("com.thoughtworks.com.mapper.ProductMapper.all");
    }

    public Product find(long id) {
        return null;
    }


}
