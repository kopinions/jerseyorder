package com.thoughtworks.com;

import com.thoughtworks.com.domain.MybatisExecutor;
import com.thoughtworks.com.domain.Product;
import com.thoughtworks.com.domain.ProductCatalog;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCatalogTest {
    @Test
    public void should_get_product_list() {
        SqlSession session = new MybatisExecutor().getSession();
        ProductCatalog productCatalog = new ProductCatalog(session);
//        productCatalog.create("name1");
//        productCatalog.create("name2");
//        productCatalog.create("name3");

        List<Product> productList = productCatalog.getProductList();
        assertThat(productList.size(), is(3));
        assertThat(productList.get(0).getId(), is(1L));
    }
//
//    @Test
//    public void should_find_product_by_id() {
//        SqlSession session = new MybatisExecutor().getSession();
//        ProductCatalog productCatalog = new ProductCatalog(session);
//        productCatalog.create("name1");
//            assertThat(productCatalog.find(1).getId(), is(1l));
//    }
}
