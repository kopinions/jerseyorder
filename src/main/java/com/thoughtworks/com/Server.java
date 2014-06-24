package com.thoughtworks.com;

import com.thoughtworks.com.api.PricesResource;
import com.thoughtworks.com.api.ProductsResource;
import com.thoughtworks.com.domain.MybatisExecutor;
import com.thoughtworks.com.json.PriceJson;
import com.thoughtworks.com.json.ProductsRefJson;
import org.apache.ibatis.session.SqlSession;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;

import java.io.IOException;
import java.net.URI;

public class Server {


    public static void main(String [] args) {
        SqlSession session = new MybatisExecutor().getSession();
//        IProductCatalog mockProductCatalog = new ProductCatalog(session);
//        IPriceRepository mockPriceRepository = new PriceRepository(session);


        ResourceConfig configuration = new ResourceConfig();
        configuration.registerResources(Resource.from(ProductsResource.class));
        configuration.registerResources(Resource.from(PricesResource.class));

//        configuration.register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(mockPriceRepository).to(IPriceRepository.class);
//                bind(mockProductCatalog).to(IProductsCatalog.class);
//            }
//        });

        configuration.packages("com.thoughtworks.com.json").register(PriceJson.class)
                .register(ProductsRefJson.class)
                .register(JacksonFeature.class);


        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8000"), configuration);

        try {
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
