package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户表(TableUser)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:30
 */
@RestController
@RequestMapping("/tableUser")
public class TableUserController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private TableUserService tableUserService;

    @PostMapping("/register")
    public boolean userRegister(TableUser user) {
        user.setUPwd(passwordEncryption(user.getUPwd()));
        return tableUserService.save(user);
    }

    private String passwordEncryption(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //加密方法
        return encoder.encode(pwd);
    }
}