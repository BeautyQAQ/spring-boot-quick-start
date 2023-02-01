package com.quick.start.special;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringBootSpecial {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpecial.class, args);
    }
}
