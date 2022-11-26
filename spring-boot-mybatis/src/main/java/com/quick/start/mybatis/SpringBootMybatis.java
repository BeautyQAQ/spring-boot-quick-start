package com.quick.start.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan 注解，扫描对应 Mapper 接口所在的包路径。
@MapperScan(basePackages = "com.quick.start.mybatis.mapper")
public class SpringBootMybatis {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatis.class, args);
    }
}
