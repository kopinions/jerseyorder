package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Price;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class PriceRefJson {
    private Price currentPrice;
    private UriBuilder uriBuilder;

    public PriceRefJson(Price currentPrice, UriBuilder uriBuilder) {
        this.currentPrice = currentPrice;
        this.uriBuilder = uriBuilder;
    }

    public PriceRefJson() {
    }

    public double getPrice() {
        return currentPrice.getPrice();
    }

    public URI getUri() {
        return uriBuilder.path("/prices/" + String.valueOf(currentPrice.getId())).build();
    }
}
