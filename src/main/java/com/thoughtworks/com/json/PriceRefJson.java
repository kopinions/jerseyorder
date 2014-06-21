package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Price;

public class PriceRefJson {
    private Price currentPrice;

    public PriceRefJson(Price currentPrice) {
        this.currentPrice = currentPrice;
    }

    public PriceRefJson() {
    }

    public double getPrice() {
        return currentPrice.getPrice();
    }
}
