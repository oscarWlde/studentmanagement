package com.example.demo.apiconfig;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("File API").description(
                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.").
                		contact(new io.swagger.v3.oas.models.info.Contact().url("http://zyyui.com").name("ZYY").email("ZYYhaha@com.cn")));
        }
}