package com.thoughtworks.com.domain;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisExecutor {

    private final SqlSessionFactory sqlSessionFactory;

    public MybatisExecutor()  {
//
//        PGPoolingDataSource source = new  PGPoolingDataSource();
//        source.setUser("twer");
//        source.setDatabaseName("order");
//        try {
//            source.setUrl("jdbc:postgresql://localhost:5432/order");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, source);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(ProductMapper.class);
//        configuration.addMapper(PriceMapper.class);
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        //


        String resource = "com/thoughtworks/com/mapper/config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void init() {
        String resource = "com/thoughtworks/com/mapper/config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
