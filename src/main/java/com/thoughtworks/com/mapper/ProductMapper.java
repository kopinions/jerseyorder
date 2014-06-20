package com.thoughtworks.com.mapper;

import com.thoughtworks.com.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("SELECT * FROM products WHERE id = #{id}")
    Product find(int id);

    @Select("SELECT * FROM products a left join prices b on a.id=b.productId")
    List<ProductWithPrice   > all(ProductHandler handler);


    @Select("show databases")
    Product show();
}
