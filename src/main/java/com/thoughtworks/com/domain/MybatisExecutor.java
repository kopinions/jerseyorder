package com.thoughtworks.com.domain;

import com.thoughtworks.com.mapper.ProductMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.SQLException;

public class MybatisExecutor {

    private final SqlSessionFactory sqlSessionFactory;

    public MybatisExecutor()  {

        PGPoolingDataSource source = new  PGPoolingDataSource();
        source.setUser("twer");
        source.setDatabaseName("order");
        try {
            source.setUrl("jdbc:postgresql://localhost:5432/order");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, source);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAlias(Product.class);
        configuration.addMapper(ProductMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
