package com.thoughtworks.com;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/19/14
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class PriceRepositoryTest {
    @Test
    public void should_create_price_for_product() {
        PriceRepository priceRepository = new PriceRepository();
        Product product1 = new ProductCatalog().create("product1");
        priceRepository.create(product1, new Date(), 1.1);
        assertThat(priceRepository.list(product1.getId()).size(), is(1));
    }
}
