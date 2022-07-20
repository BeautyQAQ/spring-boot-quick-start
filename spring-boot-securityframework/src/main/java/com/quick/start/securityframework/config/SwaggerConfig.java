package com.quick.start.securityframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启Swagger
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")// 组名称
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quick.start.securityframework.controller"))// 扫描的包
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 该套 API 说明，包含作者、简介、版本、等信息
     *
     * @return ApiInfo
     */
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("本文档描述了接口定义")
                .version("1.0")
                .build();
    }
}
