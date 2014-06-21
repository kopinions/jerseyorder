package com.thoughtworks.com.domain;


import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;


@Provider
public class PriceRepository implements IPriceRepository {

    @Override
    public List<Price> all() {
        return new ArrayList<>();
    }

    @Override
    public Price save(Price price) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
