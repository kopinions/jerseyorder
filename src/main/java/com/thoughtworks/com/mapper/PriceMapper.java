package com.thoughtworks.com.mapper;

import com.thoughtworks.com.domain.Price;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface PriceMapper {
    @Select("insert into prices (productId, price, effectDate) values (#{productId}, #{price}, #{effectDate}) returning id")
    public int createPrice(@Param("productId") int productId, @Param("price") double price, @Param("effectDate") Date effectDate);

    @Select("select * from prices where productId=#{id}")
    List<Price> getPrice(@Param("id") double productId);
}
