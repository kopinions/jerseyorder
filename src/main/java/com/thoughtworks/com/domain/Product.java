package com.thoughtworks.com.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private int id;
    private List<Price> historyPrices;
//    private Price currentPrice;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
        historyPrices = new ArrayList<>();
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void addHistoryPrice(Price... price) {
        Arrays.asList(price).stream().filter(p-> !historyPrices.contains(p)).forEach(p-> historyPrices.add(p));
    }

    public List<Price> getHistoryPrice() {
        return historyPrices;
    }

    public String getName() {
        return name;
    }
}
