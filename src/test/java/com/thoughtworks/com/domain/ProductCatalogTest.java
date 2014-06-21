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
        ProductCatalog productCatalog = new ProductCatalog(session);
        List<Product> productList = productCatalog.getProductList();
        assertThat(productList.size(), is(3));
        assertThat(productList.stream().anyMatch(p -> p.getId() == 1), is(true));

        List<Price> historyPriceForProduct1 = productList.stream().filter(p -> p.getId() == 1).findFirst().get().getHistoryPrice();
        assertThat(historyPriceForProduct1.stream().allMatch(p-> p.getProductId() ==1), is(true));
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
