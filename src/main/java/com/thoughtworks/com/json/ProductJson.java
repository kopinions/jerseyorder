package com.thoughtworks.com.json;

import com.thoughtworks.com.domain.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ProductJson{
    private Product product;
    private UriInfo uriInfo;

    public ProductJson() {

    }

    public ProductJson(Product product, UriInfo uriInfo) {
        this.product = product;
        this.uriInfo = uriInfo;
    }

    public URI getUri() {
        return uriInfo.getAbsolutePathBuilder().build();
    }


    public String getName() {
        return product.getName();
    }

    public Map<String, Object> getPrice() {
        HashMap<String, Object> priceMap = new HashMap<>();
        priceMap.put("price", product.getCurrentPrice().getPrice());
        priceMap.put("productId", product.getId());
        return priceMap;
    }
}
