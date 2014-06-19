package com.thoughtworks.com;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductCatalogTest {
    @Test
    public void should_get_product_list() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.create("name1");
        productCatalog.create("name2");
        productCatalog.create("name3");

        assertThat(productCatalog.getProductList().size(), is(3));
    }

    @Test
    public void should_find_product_by_id() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.create("name1");
        try {
            assertThat(productCatalog.find(1).getId(), is(1l));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
