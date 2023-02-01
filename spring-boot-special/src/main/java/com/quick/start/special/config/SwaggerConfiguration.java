package com.quick.start.special.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 标记项目启用 Swagger API 接口文档
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quick.start.special.controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试接口文档示例")
                .description("我是一段描述")
                .version("1.0.0") // 版本号
                .contact(new Contact("测试", "http://www.baidu.com", "142536@gmail.com")) // 联系人
                .build();
    }

}
