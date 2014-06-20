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
}
