package com.quick.start.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

@SpringBootApplication
public class SpringBootConfiguration {

    /**
     * 设置需要读取的配置文件的名字。
     * 基于 {@link org.springframework.boot.context.config.ConfigFileApplicationListener#CONFIG_NAME_PROPERTY} 实现。
     */
    private static final String CONFIG_NAME_VALUE = "application,rpc";

    public static void main(String[] args) {
        // <X> 设置环境变量
        System.setProperty(ConfigFileApplicationListener.CONFIG_NAME_PROPERTY, CONFIG_NAME_VALUE);
        // 传递参数
        args = new String[]{"--jasypt.encryptor.password=justdoit", "--start-arg=class"};
        SpringApplication.run(SpringBootConfiguration.class, args);
    }
}
