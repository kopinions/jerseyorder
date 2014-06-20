package com.thoughtworks.com.domain;

public class Product {
    private String name;
    private Long id;
//    private List<Price> historyPrices;
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
}
