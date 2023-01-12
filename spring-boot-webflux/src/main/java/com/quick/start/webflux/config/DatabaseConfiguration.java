package com.quick.start.webflux.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableTransactionManagement // 开启事务的支持
public class DatabaseConfiguration {

    /**
     * #connectionFactory(properties) 方法，创建 JasyncConnectionFactory Bean 对象。
     * 因为 spring-boot-starter-data-r2dbc 支持 R2DBC 的自动化配置，
     * 但是暂不支持自动创建 JasyncConnectionFactory 作为 ConnectionFactory Bean ，所以这里我们需要自定义。
     * @param properties properties
     * @return ConnectionFactory
     * @throws URISyntaxException URISyntaxException
     */
    @Bean
    public ConnectionFactory connectionFactory(R2dbcProperties properties) throws URISyntaxException {
        // 从 R2dbcProperties 中，解析出 host、port、database
        URI uri = new URI(properties.getUrl());
        String host = uri.getHost();
        int port = uri.getPort();
        String database = uri.getPath().substring(1); // 去掉首位的 / 斜杠
        // 创建 jasync Configuration 配置配置对象
        com.github.jasync.sql.db.Configuration configuration = new com.github.jasync.sql.db.Configuration(
                properties.getUsername(), host, port, properties.getPassword(), database);
        // 创建 JasyncConnectionFactory 对象
        return  new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
    }

    /**
     * #transactionManager(properties) 方法，创建响应式的 R2dbcTransactionManager 事务管理器。
     * @param properties properties
     * @return ReactiveTransactionManager
     * @throws URISyntaxException URISyntaxException
     */
    @Bean
    public ReactiveTransactionManager transactionManager(R2dbcProperties properties) throws URISyntaxException {
        return new R2dbcTransactionManager(this.connectionFactory(properties));
    }

}
