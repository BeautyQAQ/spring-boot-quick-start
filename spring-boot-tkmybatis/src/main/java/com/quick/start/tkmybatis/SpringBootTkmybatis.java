package com.quick.start.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.quick.start.tkmybatis.mapper")
public class SpringBootTkmybatis {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTkmybatis.class, args);
    }
}
