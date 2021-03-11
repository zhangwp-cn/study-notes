package com.example.springdemo2.config;

import org.springframework.beans.factory.annotation.Value;
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

/**
 * @author zhangwp
 * @date 2021/3/11 - 11:58
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String swaggerDomain;
    private boolean swaggerEnable;

    public SwaggerConfig(@Value("${swagger.rootUrl:localhost:8080}") String swaggerDomain,
                         @Value("${swagger.enable:false}") boolean swaggerEnable) {
        this.swaggerDomain = swaggerDomain;
        this.swaggerEnable = swaggerEnable;
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("spring-demo2 接口")
                .description("spring-demo2 接口")
                .version("/api/v1")
                .contact(new Contact("zhangwp", "", "zhangwpeng.cn@gmail.com"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerDomain)
                .useDefaultResponseMessages(false)
                .enable(swaggerEnable)
                .apiInfo(apiInfo)

                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.springdemo2"))
                .paths(PathSelectors.any())
                .build();
    }

}
