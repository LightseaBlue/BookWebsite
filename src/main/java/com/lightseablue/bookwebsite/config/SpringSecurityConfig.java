package com.lightseablue.bookwebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Program: bookwebsite
 * @ClassName: SpringSecurityConfig
 * @Author: LightseaBlue
 * @Date: 2020-12-18 20:58
 * @Version: V1.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("securityServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 请求授权规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user").authenticated()
                .antMatchers("/user2").hasRole("vip");

        http.formLogin().permitAll();
        http.rememberMe();
        http.logout().permitAll();
    }

    /**
     * 认证规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义
//        auth.inMemoryAuthentication()
//                .withUser("kuangshen").password("123456").roles("vip2", "vip3")
//                .and()
//                .withUser("root").password("123456").roles("vip1", "vip2", "vip3")
//                .and()
//                .withUser("guest").password("123456").roles("vip1", "vip2");
        //在jdbc中
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
