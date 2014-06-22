package com.thoughtworks.com.json;


import com.thoughtworks.com.domain.Price;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.text.SimpleDateFormat;

public class PriceJson {
    private SimpleDateFormat dateFormatter;
    private Price price;
    private UriInfo uriInfo;

    public PriceJson(Price price, UriInfo uriInfo) {
        this.price = price;
        this.uriInfo = uriInfo;
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss a Z");
    }

    public PriceJson() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss a Z");
    }

    public double getPrice() {
        return price.getPrice();
    }

    public URI getUri() {
        return uriInfo.getAbsolutePathBuilder().path(String.valueOf(price.getId())).build();
    }


    public String getEffectDate() {
        return dateFormatter.format(price.getEffectDate());
    }

}
