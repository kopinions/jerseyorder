package com.thoughtworks.com;

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
