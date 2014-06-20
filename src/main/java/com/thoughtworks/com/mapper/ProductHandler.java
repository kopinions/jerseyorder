package com.thoughtworks.com.mapper;


import com.thoughtworks.com.domain.Price;
import com.thoughtworks.com.domain.Product;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;


public class ProductHandler implements ResultHandler {
    List<Product> allProducts;

    public ProductHandler() {
        allProducts = new ArrayList<>();
    }

    @Override
    public void handleResult(ResultContext context) {
        Object resultObject = context.getResultObject();
        ProductWithPrice pwp = (ProductWithPrice) resultObject;
        Product product = new Product(pwp.name, pwp.id);

        if (allProducts.contains(product) && pwp.productId > 0) {
            Product existProduct = allProducts.get(allProducts.indexOf(product));
            existProduct.addHistoryPrice(new Price(pwp.productId, pwp.time, pwp.price));

        } else {
            if (pwp.productId > 0) {
                product.addHistoryPrice(new Price(pwp.productId, pwp.time, pwp.price));
            }
            allProducts.add(product);
        }
    }

    public List<Product> all() {
        return allProducts;
    }
}
