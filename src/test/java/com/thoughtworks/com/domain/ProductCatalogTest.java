package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCatalogTest {
    @Test
    public void should_get_product_list() {
        SqlSession session = new MybatisExecutor().getSession();
        IProductsCatalog mapper = session.getMapper(IProductsCatalog.class);
        List<Product> productList = mapper.getProductList();
        assertThat(productList.size(), is(3));
        assertThat(productList.stream().anyMatch(p -> p.getId() == 1), is(true));
    }

    @Test
    public void should_find_product_by_id() {
        SqlSession session = new MybatisExecutor().getSession();
        IProductsCatalog mapper = session.getMapper(IProductsCatalog.class);
        Product product = mapper.find(1);
        assertThat(product.getId(), is(1));
        assertThat(product.getName(), is("products1"));
    }
}
