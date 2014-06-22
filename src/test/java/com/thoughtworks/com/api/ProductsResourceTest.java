package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IProductsCatalog;
import com.thoughtworks.com.domain.Price;
import com.thoughtworks.com.domain.Product;
import com.thoughtworks.com.domain.ProductCatalog;
import com.thoughtworks.com.json.PriceJson;
import com.thoughtworks.com.json.PriceRefJson;
import com.thoughtworks.com.json.ProductJson;
import com.thoughtworks.com.json.ProductsRefJson;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsResourceTest extends JerseyTest{

    @org.junit.Test
    public void should_get_product() {
        Response response = target("/products").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
//        System.out.println(response.readEntity(String.class));
        List list = response.readEntity(List.class);
        Map<String, Object> a = (Map<String, Object>) list.get(0);
        assertThat(a.get("uri").toString(), endsWith("/products/1"));
        assertThat(a.get("name").toString(), is("product"));
        assertThat(a.get("location").toString(), is("location"));
        Map<String, Object> price= (Map<String, Object>) a.get("price");
        assertThat(price.get("uri").toString(), endsWith("prices/1"));

    }

    @org.junit.Test
    public void should_get_product_by_id() {
        Response response = target("/products/1").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
        Map product = response.readEntity(Map.class);
        assertThat(product.get("uri").toString(), endsWith("/products/1"));
        assertThat(product.get("name").toString(), is("product"));
        assertThat(product.get("location").toString(), is("location"));
        Map<String, Object> price = (Map<String, Object>) product.get("price");
        assertThat(price.get("price").toString(), is("1.0"));
    }

    @Override
    protected Application configure() {

        ProductCatalog catalog = mock(ProductCatalog.class);
        Product product = new Product(1, "product", "location");
        product.addHistoryPrice(new Price(1, new Date(), 1, 1));
        when(catalog.getProductList()).thenReturn(Arrays.asList(product));
        when(catalog.find(anyInt())).thenReturn(product);
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.registerResources(Resource.from(ProductsResource.class));
        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(catalog).to(IProductsCatalog.class);
            }
        });
        resourceConfig.packages("com.thoughtworks.com.json")
                .register(ProductJson.class)
                .register(PriceJson.class)
                .register(ProductsRefJson.class)
                .register(PriceRefJson.class)
                .register(JacksonFeature.class);

        return resourceConfig;
    }
}
