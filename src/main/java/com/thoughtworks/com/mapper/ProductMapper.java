package com.thoughtworks.com.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("SELECT product.id as productId, product.name as productName, product.location as productLocation, price.id as priceId, price.price as price, price.effectDate as effectDate FROM products product left join prices price on product.id=price.productId where product.id=#{id}")
    List<ProductWithPrice> find(@Param("id") int id, ProductHandler handler);

    @Select("SELECT product.id as productId, product.name as productName, product.location as productLocation, price.id as priceId, price.price as price, price.effectDate as effectDate FROM products product left join prices price on product.id=price.productId")
    List<ProductWithPrice> all(ProductHandler handler);
}
