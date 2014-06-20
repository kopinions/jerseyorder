package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IPriceRepository;
import com.thoughtworks.com.domain.Price;
import com.thoughtworks.com.domain.Product;
import com.thoughtworks.com.json.PriceJson;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;


public class PricesResource {
    private final Product product;
    private final UriInfo uriInfo;

    IPriceRepository priceRepo;

    public PricesResource(Product product, UriInfo uriInfo, IPriceRepository priceRepo) {
        this.product = product;
        this.uriInfo = uriInfo;
        this.priceRepo = priceRepo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceJson> productPrices() {
        List<Price> collect = priceRepo.all().stream().filter(p -> p.getProductId() == product.getId()).collect(Collectors.toList());
        return collect.stream().map(p -> new PriceJson(p)).collect(Collectors.toList());
    }

}
