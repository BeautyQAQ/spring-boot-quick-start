package com.quick.start.autoconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my.server")
public class ServerProperties {

    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8000;

    /**
     * 端口
     */
    public Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort(){
        return port;
    }

    public ServerProperties setPort(Integer port){
        this.port = port;
        return this;
    }
}
