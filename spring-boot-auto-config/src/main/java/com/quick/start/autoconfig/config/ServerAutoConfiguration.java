package com.quick.start.autoconfig.config;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 声明配置类
 * 使 ServerProperties 配置属性类生效
 */
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class ServerAutoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(ServerAutoConfiguration.class);

    /**
     * 声明创建
     * Bean需要项目中存在 com.sun.net.httpserver.HttpServer 类。该类为 JDK 自带，所以一定成立。
     *
     * @param serverProperties 配置类
     * @return HttpServer
     * @throws IOException IOException
     */
    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(ServerProperties serverProperties) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(serverProperties.getPort()), 0);
        httpServer.start();
        logger.info("[httpServer][启动服务器成功，端口为:{}]", serverProperties.getPort());
        return httpServer;
    }
}
