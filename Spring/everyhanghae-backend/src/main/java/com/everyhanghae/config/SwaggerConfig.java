package com.everyhanghae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
        .title("애브리 항해") // 스웨거 제목
        .description("애브리 항해 API") // 스웨거 내용
        .build();
    }


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("애브리 항해 API")// 그룹명
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }



}
