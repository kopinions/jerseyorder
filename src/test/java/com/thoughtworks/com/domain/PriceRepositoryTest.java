package com.thoughtworks.com.domain;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceRepositoryTest {
    @Test
    public void should_save_price_in_storage() {
        SqlSession session = new MybatisExecutor().getSession();
        PriceRepository priceRepository = new PriceRepository(session);
        int priceId = priceRepository.save(1, new Date(), 1.0);
        assertThat(priceId>1, is(true));
    }
}
