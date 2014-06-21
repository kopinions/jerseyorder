package com.thoughtworks.com.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/19/14
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Price {
    private long productId;
    private Date time;
    private double price;
    private int id;

    public Price(long productId, Date time, double price) {
        this.productId = productId;
        this.time = time;
        this.price = price;
    }

    public Price(long productId, Date time, double price, int id) {
        this.productId = productId;
        this.time = time;
        this.price = price;
        this.id = id;
    }

    public Price() {
    }

    public double getPrice() {
        return price;
    }

    public long getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }
}
