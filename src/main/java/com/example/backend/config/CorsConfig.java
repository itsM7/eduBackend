package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins//("*")
//                                "http://addc727f3ec904f879fedfede0d45e19-1553223092.eu-west-1.elb.amazonaws.com",
                                ("http://localhost:3000", "http://a9e269fef77344a6d82cb83b42ef9759-736446138.eu-west-1.elb.amazonaws.com"   )
                        .allowedMethods("*")//("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
