package com.thoughtworks.com.mapper;

import com.thoughtworks.com.domain.Product;
import org.apache.ibatis.annotations.Select;

public interface ProductMapper {
    @Select("SELECT * FROM products WHERE id = #{id}")
    Product find(int id);

    @Select("SELECT * FROM products")
    Product all();


    @Select("show databases")
    Product show();
}
