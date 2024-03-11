//package com.maverick_shravan.User_Service.Configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.SwaggerUiConfigParameters;
//import org.springdoc.core.SwaggerUiConfigProperties;
//
//@Configuration
//@EnableWebMvc
//public class SwaggerConfiguration {
//
//    @Bean
//    public GroupedOpenApi customApi() {
//        return GroupedOpenApi.builder()
//                .group("custom-api") // Group name
//                .pathsToMatch("/api/**") // API paths to include
//                .build();
//    }
//
//    @Bean
//    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//        return new SwaggerUiConfigParameters();
//    }
//
//    @Bean
//    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
//        return new SwaggerUiConfigProperties();
//    }
//}
