package com.thoughtworks.com;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/19/14
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Price {
    public long productId;
    private Date time;
    private double price;

    public Price(long productId, Date time, double price) {
        this.productId = productId;
        this.time = time;
        this.price = price;
    }
}
