package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceRepositoryTest {
    SqlSession session;

    @Before
    public void setUp() throws Exception {
        session = new MybatisExecutor().getSession();
    }

    @Test
    public void should_save_price_in_storage() {
        IProductsCatalog productsCatalog = session.getMapper(IProductsCatalog.class);
        Date date = new Date();
        Price price = new Price(1.0, date, 1);
        productsCatalog.createProductPrice(productsCatalog.find(1), price);
        assertThat(price.getId()>0, is(true));
        List < Price > prices = productsCatalog.getProductList().stream().filter(p -> p.getId() == 1).map(Product::getCurrentPrice).collect(toList());
        assertThat(prices.stream().anyMatch(p -> p.getEffectDate().compareTo(date) == 0), is(true));
        session.close();
    }
}
