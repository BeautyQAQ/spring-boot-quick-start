package com.quick.start.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS) spring boot 3.0支持
@SpringBootTest(args = {"--jasypt.encryptor.password=justdoit", "--start-arg=class", "--spring.config.name=application,rpc"})  // 直接在启动类上添加配置
public class JasyptTest {

    @Resource
    private StringEncryptor encryptor;

    @Test
    public void encode(){
        String springApplicationName = "demo-application";
        System.out.println(encryptor.encrypt(springApplicationName));
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    public void print() {
        System.out.println(applicationName);
    }
}
