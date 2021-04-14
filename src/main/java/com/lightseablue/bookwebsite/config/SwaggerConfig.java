package com.lightseablue.bookwebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * SwaggerUi配置类
 *
 * @Program: bookwebsite
 * @ClassName: SwaggerConfig
 * @Author: LightseaBlue
 * @Date: 2020-12-16 22:37
 * @Version: V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);
        System.out.println(b);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(b)
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.lightseablue.bookwebsite.controll"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "LightseaBlue",
                "https://blog.csdn.net/Sakitaf",
                "fox1172893066@qq.com");
        return new ApiInfo(
                // 标题
                "BookWebsite",
                // 描述
                "听书网Swagger",
                // 版本
                "v1.0",
                // 组织链接
                "https://blog.csdn.net/Sakitaf",
                // 作者信息
                contact,
                // 许可
                "Apache 2.0",
                // 许可连接
                "http://www.apache.org/licenses/LICENSE-2.0",
                // 扩展
                new ArrayList<>()
        );

    }
}

