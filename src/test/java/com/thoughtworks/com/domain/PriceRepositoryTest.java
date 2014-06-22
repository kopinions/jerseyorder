package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceRepositoryTest {
    @Test
    public void should_save_price_in_storage() {
        SqlSession session = new MybatisExecutor().getSession();
        PriceRepository priceRepository = new PriceRepository(session);
        Date date = new Date();
        int priceId = priceRepository.save(1, date, 1.0);
        SqlSession session1 = new MybatisExecutor().getSession();
        ProductCatalog productCatalog = new ProductCatalog(session1);
        List<Price> prices = productCatalog.getProductList().stream().filter(p -> p.getId() == 1).map(p -> p.getHistoryPrice()).iterator().next();
        assertThat(prices.stream().anyMatch(p -> p.getId() == priceId), is(true));
    }


    @Test
    public void should_get_all_price_of_product() {
        SqlSession session = new MybatisExecutor().getSession();
        PriceRepository priceRepository = new PriceRepository(session);
        Date date = new Date();
        int priceId = priceRepository.save(1, date, 1.0);
        List<Price> prices = priceRepository.getPrices(1);
        assertThat(prices.stream().anyMatch(p -> p.getId() == priceId), is(true));
    }
}
