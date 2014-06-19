package com.thoughtworks.com;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsResourceTest extends JerseyTest{

    @org.junit.Test
    public void should_get_product() {
        Response response = target("/products").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(), is(200));
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(ProductsResource.class);
    }
}
