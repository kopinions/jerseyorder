package com.thoughtworks.com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PriceRepository {
    private List<Price> priceList = new ArrayList<>();

    public void create(Product product, Date date, double price) {
        priceList.add(new Price(product.getId(), date, price));
    }

    public List<Price> list(long productId) {
        if (priceList.size() != 0) {
            return priceList.stream().filter(p -> p.productId == productId).collect(Collectors.toList());
        }
        return priceList;
    }
}
