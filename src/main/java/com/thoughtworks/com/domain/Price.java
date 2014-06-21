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
    private int productId;
    private Date effectDate;
    private double price;
    private int id;

    public Price(int productId, Date effectDate, double price) {
        this.productId = productId;
        this.effectDate = effectDate;
        this.price = price;
    }

    public Price(int productId, Date effectDate, double price, int id) {
        this.productId = productId;
        this.effectDate = effectDate;
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

    public Date getEffectDate() {
        return effectDate;
    }
}
