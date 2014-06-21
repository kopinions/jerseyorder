package com.thoughtworks.com.api;

import com.thoughtworks.com.domain.IProductsCatalog;
import com.thoughtworks.com.domain.Product;
import com.thoughtworks.com.domain.ProductCatalog;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsResourceTest extends JerseyTest{

    @org.junit.Test
    public void should_get_product() {
        Response response = target("/products").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
        List list = response.readEntity(List.class);
        Map<String, Integer> a = (HashMap<String, Integer>)list.get(0);
        assertThat(a.get("id"), is(1));
    }

    @org.junit.Test
    public void should_get_product_by_id() {
        Response response = target("/products/1").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
        Map product = response.readEntity(Map.class);
        assertThat(product.get("uri").toString(), endsWith("/products/1"));
        assertThat(product.get("name").toString(), is("product"));
    }

    @Override
    protected Application configure() {

        ProductCatalog catalog = mock(ProductCatalog.class);
        when(catalog.getProductList()).thenReturn(Arrays.asList(new Product("product", 1)));
        when(catalog.find(anyLong())).thenReturn(new Product("product", 1));
        ResourceConfig resourceConfig = new ResourceConfig(ProductsResource.class);

        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(catalog).to(IProductsCatalog.class);
            }
        });
//        resourceConfig
//                .register(ProductsRefJson.class)
//                .register(ProductJson.class)
//                .register(PriceJson.class)
//                .register(JacksonFeature.class);
        return resourceConfig;
    }
}
