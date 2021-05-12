package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableAdmin;
import com.lightseablue.bookwebsite.service.TableAdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 管理员(TableAdmin)表控制层
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:34
 */
@RestController
@RequestMapping("/tableAdmin")
public class TableAdminController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAdminService tableAdminService;

    /**
     * 验证登陆
     *
     * @param adminName
     * @param passWord
     * @return
     */
    @PostMapping("/AdminLogin")
    private Integer adminLogin(String adminName, String passWord, HttpServletRequest request) {
        TableAdmin tableAdmin = tableAdminService.verifyLogin(adminName, passWord);
        if (tableAdmin != null) {
            request.getSession().setAttribute("admin", tableAdmin);
            return 1;
        }
        return 2;
    }

    @PostMapping("/upDateAdminPwd")
    private Integer upDateAdminPwd(String oldPwd, String newPwd, Integer aId) {
        boolean admin = tableAdminService.isAdmin(aId, oldPwd);
        if (admin) {
            boolean b = tableAdminService.upDateAdminPwd(aId, newPwd);
            if (b) {
                return 1;
            } else {
                return 3;
            }
        } else {
            return 2;
        }
    }
}
