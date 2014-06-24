package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCatalogTest {
    SqlSession session;

    @Before
    public void setUp() throws Exception {

        session = new MybatisExecutor().getSession();
    }

    @Test
    public void should_get_product_list() {
        IProductsCatalog mapper = session.getMapper(IProductsCatalog.class);
        List<Product> productList = mapper.getProductList();
        assertThat(productList.size()>0, is(true));
        assertThat(productList.stream().anyMatch(p -> p.getId() == 1), is(true));
    }

    @Test
    public void should_find_product_by_id() {
        IProductsCatalog mapper = session.getMapper(IProductsCatalog.class);
        Product product = mapper.find(1);
        assertThat(product.getId(), is(1));
        assertThat(product.getName(), is("products1"));
    }

    @After
    public void tearDown() throws Exception {
        session.close();
    }

    @Test
    public void should_create_product() {
        IProductsCatalog mapper = session.getMapper(IProductsCatalog.class);
        Product product = new Product("product1", "beijing");
        mapper.createProduct(product);
        assertThat(product.getId()>1, is(true));
    }
}
