package com.thoughtworks.com.api;


import com.thoughtworks.com.domain.*;
import com.thoughtworks.com.json.PriceJson;
import com.thoughtworks.com.json.ProductsRefJson;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductPriceResourceTest extends JerseyTest {

    @Test
    public void should_get_prices_of_product() {
        Response response = target("/products/1/prices").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
        List allPrices = response.readEntity(List.class);
        assertThat(allPrices.size(), is(2));
        Map<String, Object> firstPrice = (Map<String, Object>) allPrices.get(0);
        assertThat(firstPrice.get("productId").toString(), is("1"));
    }

    @Override
    protected Application configure() {
        ProductCatalog mockProductCatalog = mock(ProductCatalog.class);
        when(mockProductCatalog.find(1)).thenReturn(new Product("product1", 1));
        PriceRepository mockPriceRepository = mock(PriceRepository.class);
        when(mockPriceRepository.all()).thenReturn(Arrays.asList(new Price(1, new Date(), 1.1), new Price(1, new Date(), 1.2)));

        ResourceConfig configuration = new ResourceConfig();
        configuration.registerResources(Resource.from(ProductsResource.class));
        configuration.registerResources(Resource.from(PricesResource.class));

        configuration.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(mockPriceRepository).to(IPriceRepository.class);
                bind(mockProductCatalog).to(IProductsCatalog.class);
            }
        });

        configuration.packages().register(PriceJson.class)
                .register(ProductsRefJson.class)
                .register(JacksonFeature.class);

        return configuration;
    }
}
