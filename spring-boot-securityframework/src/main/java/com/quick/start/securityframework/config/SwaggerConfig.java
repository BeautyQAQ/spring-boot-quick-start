package com.quick.start.securityframework.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//表明这是一个配置类
@EnableSwagger2//开启Swagger
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")//组名称
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quick.start.securityframework.controller"))//扫描的包
                .paths(PathSelectors.any())
                .build();

    }
    /**
     * 该套 API 说明，包含作者、简介、版本、等信息
     * @return
     */
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("my-springsecurity-plus-API文档")
                .description("本文档描述了my-springsecurity-plus接口定义")
                .version("1.0")
                .build();
    }

}