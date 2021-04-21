package com.lightseablue.bookwebsite.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName: CodingFilter
 * @Package: com.lightseablue.bookwebsite.filter
 * @Description: 字符过滤器
 * @author: LightseaBlue
 * @date: 2021/4/15     12:25
 */
@WebFilter(filterName = "CodingFilter", urlPatterns = "/*")
public class CodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
