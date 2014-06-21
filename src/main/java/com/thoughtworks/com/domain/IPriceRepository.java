package com.thoughtworks.com.domain;

import java.util.List;

public interface IPriceRepository {
    List<Price> all();

    Price save(Price price);
}
