package com.thoughtworks.com.domain;

import java.util.Date;
import java.util.List;

public interface IPriceRepository {
    List<Price> all();

    int save(int productId, Date date, double price);

    List<Price> getPrices(int productId);

}
