package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProductPriceTest extends JerseyTest {

    Date priceEffectiveTime = new Date();

    class CreatePriceRquest{
        int productId;
        double price;
        Date effectTime;
    }

    @Test
    public void should_create_price_for_product_and_get_price_uri_in_location() {
        MultivaluedMap<String, String> post = new MultivaluedHashMap<>();
        post.putSingle("price", "1.0");
        post.putSingle("effectTime", priceEffectiveTime.toString());
        Response response = target("/products/1/prices").request().accept(MediaType.WILDCARD_TYPE).post(Entity.form(post));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString(), endsWith("/products/1/prices/1"));
    }

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.registerResources(Resource.from(ProductsResource.class));
        resourceConfig.registerResources(Resource.from(PricesResource.class));
        Price price = new Price(1, priceEffectiveTime, 1.0, 1);
        PriceRepository mockPriceRepository = mock(PriceRepository.class);
        when(mockPriceRepository.save(anyObject())).thenReturn(price);
        ProductCatalog mockProductCatalog = mock(ProductCatalog.class);
        when(mockProductCatalog.find(1)).thenReturn(new Product("product1", 1));
        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(mockPriceRepository).to(IPriceRepository.class);
                bind(mockProductCatalog).to(IProductsCatalog.class);
            }
        });
        HashMap<String, Object> properties = new HashMap<>();
        resourceConfig.addProperties(properties);

        return resourceConfig;
    }


}
