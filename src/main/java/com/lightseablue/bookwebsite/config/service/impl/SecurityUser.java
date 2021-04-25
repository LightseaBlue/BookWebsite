package com.lightseablue.bookwebsite.config.service.impl;

import com.lightseablue.bookwebsite.entity.TableUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author fox11
 */
public class SecurityUser extends TableUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    public SecurityUser(TableUser user) {
        if (user != null) {
            this.setUName(user.getUName());
            this.setUAbout(user.getUAbout());
            this.setUEmail(user.getUEmail());
            this.setUGoodAt(user.getUGoodAt());
            this.setUId(user.getUId());
            this.setUPwd(user.getUPwd());
            this.setUPhoto(user.getUPhoto());
            this.setUSex(user.getUSex());
            this.setUStu(user.getUStu());
        }
    }

    /**
     * 核心授权
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            //授权   要有role  源码:断言了role.startsWith("ROLE_")
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_vip");
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getUPwd();
    }

    @Override
    public String getUsername() {
        return this.getUName();
    }

    //账户是否未过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用 ,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return this.getUStu() == 1;
    }
}