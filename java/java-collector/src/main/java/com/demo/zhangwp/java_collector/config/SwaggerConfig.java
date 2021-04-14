package com.demo.zhangwp.java_collector.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String swaggerDomain;
    private boolean swaggerEnable;

    public SwaggerConfig(@Value("${swagger.rootUrl:localhost:8080}") String swaggerDomain,
                         @Value("${swagger.enable:true}") boolean swaggerEnable) {
        this.swaggerDomain = swaggerDomain;
        this.swaggerEnable = swaggerEnable;
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("monitor-collector 接口")
                .description("监控数据采集 接口")
                .version("/api/v1")
                .contact(new Contact("zhangwp","", "zhangwp@bw30.com"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerDomain)
                .useDefaultResponseMessages(false)
                .enable(swaggerEnable)
                .apiInfo(apiInfo)

                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.bw.ae.monitor"))
                .paths(PathSelectors.any())
                .build();
    }

}