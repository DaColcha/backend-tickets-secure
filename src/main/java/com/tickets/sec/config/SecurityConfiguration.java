package com.tickets.sec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                // .allowedOrigins("https://venta-tickets-leones.vercel.app/")
                .allowedOrigins("*")
                .allowedMethods("*").allowedHeaders("*");
    }
}
