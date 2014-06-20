package com.thoughtworks.com.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private Long id;
    private List<Price> historyPrices = new ArrayList<>();
//    private Price currentPrice;

    public Product(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void addHistoryPrice(Price... price) {
        Arrays.asList(price).stream().filter(p-> !historyPrices.contains(p)).forEach(p-> historyPrices.add(p));
    }

    public List<Price> getHistoryPrice() {
        return historyPrices;
    }
}
