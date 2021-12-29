package com.springboot_jpa_mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Users/NKNK-DESKTOP/Desktop/project-experience/springboot_jpa_mall/src/main/resources/static/images/");
//                .addResourceLocations("file:/Users/nknk/Desktop/project-experience/springboot_jpa_mall/src/main/resources/static/images/");
    }

}