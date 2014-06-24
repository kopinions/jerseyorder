package com.thoughtworks.com.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private int id;
    private List<Price> historyPrices;
    private Price currentPrice;
    private String location;

    public Product(int id, String name, String location) {
        this.name = name;
        this.id = id;
        this.location = location;
        historyPrices = new ArrayList<>();
    }

    public Product(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Product() {
    }

    public void addHistoryPrice(Price... price) {
        Arrays.asList(price).stream().filter(p-> !historyPrices.contains(p)).forEach(p-> historyPrices.add(p));
        currentPrice = historyPrices.stream().max((pr1,pr2)->pr1.getEffectDate().compareTo(pr2.getEffectDate())).get();
    }

    public List<Price> getHistoryPrice() {
        return historyPrices;
    }

    public String getName() {
        return name;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public String getLocation() {
        return location;
    }
}
