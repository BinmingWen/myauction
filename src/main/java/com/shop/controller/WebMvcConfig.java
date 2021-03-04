package com.shop.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : WebMvcConfig  //类名
 * @date 2020/10/26 11:32
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(".")
    }*/
    /* @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        *//*registry.addResourceHandler("/upload/**").addResourceLocations("file:E:\\picture\\");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*//*
    }*/
}
