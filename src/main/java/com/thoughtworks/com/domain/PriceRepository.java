package com.thoughtworks.com.domain;


import com.thoughtworks.com.mapper.PriceMapper;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Provider
public class PriceRepository implements IPriceRepository {
    private SqlSession session;

    public PriceRepository(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<Price> all() {
        return new ArrayList<>();
    }

    @Override
    public int save(int productId, Date date, double price) {
        int price1 = session.getMapper(PriceMapper.class).createPrice(productId, price, date);
        session.commit(true);
        return price1;
    }

    @Override
    public List<Price> getPrices(int productId) {
        List<Price> prices = session.getMapper(PriceMapper.class).getPrice(productId);
        session.commit(true);
        return prices;
    }
}
