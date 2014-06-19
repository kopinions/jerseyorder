package com.thoughtworks.com;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/19/14
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    private String name;
    private Long id;
    public Product(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
