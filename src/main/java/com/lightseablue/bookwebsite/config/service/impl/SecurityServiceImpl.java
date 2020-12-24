package com.lightseablue.bookwebsite.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SecurityServiceImpl
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:01
 */
@Service
public class SecurityServiceImpl implements UserDetailsService {
    @Autowired
    private TableUserService tableUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询数据库
        QueryWrapper<TableUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("U_NAME",userName);
        TableUser tableUser = tableUserService.getOne(queryWrapper);
        if (tableUser == null) {
            return null;
        } else {
            //授权
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_vip"));
            return new User(tableUser.getUName(), tableUser.getUPwd(), authorities);
        }
    }
}