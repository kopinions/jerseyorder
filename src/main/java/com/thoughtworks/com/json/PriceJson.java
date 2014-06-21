package com.thoughtworks.com.json;


import com.thoughtworks.com.domain.Price;

import javax.ws.rs.ext.Provider;

@Provider
public class    PriceJson {
    private Price price;

    public PriceJson(Price price) {
        this.price = price;
    }

    public double getPrice() {
        return price.getPrice();
    }

    public long getProductId() {
        return price.getProductId();
    }
}
