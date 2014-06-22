package com.thoughtworks.com.domain;

import com.thoughtworks.com.mapper.ProductHandler;
import com.thoughtworks.com.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.ext.Provider;
import java.util.List;
@Provider
public class ProductCatalog implements IProductsCatalog {
    private final SqlSession session;

    public ProductCatalog(SqlSession session) {
        this.session = session;
    }

    public Product create(String name) {
        return null;
    }

    public List<Product> getProductList() {
        ProductHandler handler = new ProductHandler();
        session.select("com.thoughtworks.com.mapper.ProductMapper.all", handler);
        session.getMapper(ProductMapper.class).all(handler);
        session.commit();
        return handler.all();
    }

    public Product find(int id) {
        ProductHandler handler = new ProductHandler();
        session.select("com.thoughtworks.com.mapper.ProductMapper.find", id, handler);
        session.commit();
        return handler.find();
    }


}
