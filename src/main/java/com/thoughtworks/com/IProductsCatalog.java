package com.thoughtworks.com;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/19/14
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IProductsCatalog {
    public Product create(String name);

    public List<Product> getProductList();

    public Product find(long id);
}
