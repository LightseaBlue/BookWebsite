package com.lightseablue.bookwebsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LightseaBlue
 */
@SpringBootApplication
@MapperScan("com.lightseablue.bookwebsite.dao")
@ServletComponentScan("com.lightseablue.bookwebsite.filter")
//开启事务 其实默认开启的
@EnableTransactionManagement
public class BookwebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookwebsiteApplication.class, args);
    }
}
