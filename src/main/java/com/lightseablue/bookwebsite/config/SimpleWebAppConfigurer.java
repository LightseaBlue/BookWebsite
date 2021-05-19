package com.lightseablue.bookwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 外部资源处理
 *
 * @author LightseaBlue
 */
@Configuration
public class SimpleWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Audio/**")
                .addResourceLocations("file:E:/Java Project/bookwebsite/Audio/");
        registry.addResourceHandler("/User/**")
                .addResourceLocations("file:E:/Java Project/bookwebsite/User/");
        registry.addResourceHandler("/Admin/**")
                .addResourceLocations("file:E:/Java Project/bookwebsite/Admin/");
        super.addResourceHandlers(registry);
    }
}