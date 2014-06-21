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
        Product product = new Product(pwp.productId, pwp.productName);

        if (allProducts.stream().anyMatch(p -> p.getId() == pwp.productId)) {
            if(pwp.priceId <=0 || pwp.effectDate==null) {
                return;
            }
            Product existProduct = allProducts.stream().filter(p->p.getId()== pwp.productId).iterator().next();
            existProduct.addHistoryPrice(new Price(pwp.productId, pwp.effectDate, pwp.price, pwp.priceId));

        } else {
            if (pwp.priceId > 0 && pwp.effectDate!=null) {
                product.addHistoryPrice(new Price(pwp.productId, pwp.effectDate, pwp.price, pwp.priceId));
            }
            allProducts.add(product);
        }
    }

    public List<Product> all() {
        return allProducts;
    }
}
