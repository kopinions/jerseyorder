package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProductPriceTest extends JerseyTest {

    Date priceEffectiveTime = new Date();
    @Test
    public void should_create_price_for_product_and_get_price_uri_in_location() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss a Z");
        MultivaluedMap<String, String> post = new MultivaluedHashMap<>();
        post.putSingle("price", "1.0");
        post.putSingle("effectTime", dateFormat.format(priceEffectiveTime));
        Response response = target("/products/1/prices").request().accept(MediaType.WILDCARD_TYPE).post(Entity.form(post));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString(), endsWith("/products/1/prices/1"));
    }

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.registerResources(Resource.from(ProductsResource.class));
        resourceConfig.registerResources(Resource.from(PricesResource.class));
        IPriceRepository mockPriceRepository = mock(IPriceRepository.class);
        when(mockPriceRepository.save(anyInt(), anyObject(), anyDouble())).thenReturn(1);
        IProductsCatalog mockProductCatalog = mock(IProductsCatalog.class);
        when(mockProductCatalog.find(1)).thenReturn(new Product(1, "product1", "location"));
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
