package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IPriceRepository;
import com.thoughtworks.com.domain.Price;
import com.thoughtworks.com.domain.Product;
import com.thoughtworks.com.json.PriceJson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PriceJson> productPrices(@Context UriInfo uriInfo) {
        List<Price> productPrices = priceRepo.getPrices(product.getId()).stream().filter(p -> p.getProductId() == product.getId()).collect(Collectors.toList());
        return productPrices.stream().map(p -> new PriceJson(p, uriInfo)).collect(Collectors.toList());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response createProductPrice(@FormParam("price") double price, @FormParam("effectTime") String effectiveTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss a Z");
        Date parse;
        try {
            parse = dateFormat.parse(effectiveTime);
        } catch (ParseException e) {
            return Response.status(400).build();
        }
        int priceId = priceRepo.save(product.getId(), parse, price);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(priceId)).build()).build();
    }
}
