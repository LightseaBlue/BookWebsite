package com.lightseablue.bookwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 外部资源处理
 */
@Configuration
public class SimpleWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Audio/**").addResourceLocations("file:E:/Java Project/bookwebsite/Audio/");
        super.addResourceHandlers(registry);
    }
}