package com.lightseablue.bookwebsite.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightseablue.bookwebsite.config.service.impl.SecurityUser;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring Security 配置类
 *
 * @ClassName: WebSecurityConfig
 * @Author: LightseaBlue
 * @Date: 2021/1/15 9:45
 * @Version: V1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * 核心  配置策略
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭post防护
        http.csrf().disable();
        //配置访问权限
        http.authorizeRequests().
                antMatchers("/upLoad").hasRole("vip")
                .antMatchers("/file/download").hasRole("vip")
                .antMatchers("/contactTheAdministrator").hasRole("vip");
        //登陆首页
        http.formLogin()
                //自义定登陆页面
                .loginPage("/login").permitAll()
                //登陆处理界面   也就是能接收到 url  和mapping一样
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("passWord")
                //登陆成功后的处理
                .successHandler(loginSuccessHandler())
                //登陆失败处理
                .failureHandler(authenticationFailureHandler())
        ;
        //登出
        http.logout().permitAll()
                //使http会话无效
                .invalidateHttpSession(true)
                //删除cookies
                .deleteCookies("JSESSIONID")
                //登出后的处理
                .logoutSuccessHandler(logoutSuccessHandler());
        //设置session会话
        http.sessionManagement()
                //最大的session会话数
                .maximumSessions(10)
                //会话失效后的跳转页面
                .expiredUrl("/login");
        //记住我功能
        http.rememberMe().rememberMeParameter("checkBox");
        //允许页面嵌套
        http.headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );
    }

    /**
     * 核心设置授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    /**
     * 登陆失败处理
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            final ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletRequest.getSession().setAttribute("loginError", "登陆失败");
                httpServletResponse.sendRedirect("/login");
            }
        };
    }

    /**
     * 密码加密策略
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                httpServletResponse.sendRedirect("/");
            }
        };
    }

    /**
     * 登陆成功处理
     *
     * @return
     */
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                request.getSession().removeAttribute("loginError");
                TableUser tableUser = (TableUser) authentication.getPrincipal();
                request.getSession().setAttribute("user", tableUser);
                logger.info("USER : " + tableUser.getUName() + " LOGIN SUCCESS !  ");
                request.getRequestDispatcher("/").forward(request, response);
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    /**
     * 自义定验证
     *
     * @return
     */
    @Bean
    public UserDetailsService myUserDetailsService() {    //用户登录实现
        return new UserDetailsService() {
            @Autowired
            private TableUserService tableUserService;

            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                TableUser user = tableUserService.getUserByName(userName);
                if (user == null) {
                    throw new UsernameNotFoundException("Username " + userName + " not found");
                }
                return new SecurityUser(user);
            }
        };
    }
}