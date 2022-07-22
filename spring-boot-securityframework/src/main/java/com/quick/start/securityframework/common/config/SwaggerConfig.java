package com.quick.start.securityframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启Swagger
 */
@EnableSwagger2
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("项目在线接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("项目接口文档")
                        //联系人信息
                        .contact(new Contact("xxxx", "xxxxxxxxxxx", "xxx@qq.com"))
                        .build())
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors.basePackage("com.quick.start.securityframework.controller"))
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
