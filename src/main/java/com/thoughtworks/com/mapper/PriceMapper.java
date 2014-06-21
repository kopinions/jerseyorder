package com.thoughtworks.com.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PriceMapper {
    @Select("insert into prices (productId, price) values (#{productId}, #{price}) returning id")
    public int createPrice(@Param("productId") int productId, @Param("price") double price);
}
