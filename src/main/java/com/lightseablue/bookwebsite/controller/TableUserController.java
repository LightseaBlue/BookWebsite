package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableUserDTO;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    /**
     * 管理员端管理用户分页查询
     *
     * @param curr
     * @return
     */
    @PostMapping("/switchUserPage")
    private List<TableUser> switchUserPage(Long curr) {
        Page<TableUser> pageUsers = tableUserService.getPageUsers(curr, null, null);
        return pageUsers.getRecords();
    }

    @PostMapping("/upDateUserStu")
    private boolean upDateUserStu(Integer uId, Integer uStu) {
        return tableUserService.upDateUserStu(uId, uStu);
    }

    @PostMapping("/conditionSwitchUserPage")
    private List<TableUserDTO> conditionSwitchUserPage(Long curr, String uName, Integer uStu) {
        if (curr == null) {
            curr = 1L;
        }
        Page<TableUser> pageUsers = tableUserService.getPageUsers(curr, uName, uStu);
        List<TableUserDTO> tableUserDTOS = new ArrayList<>();
        if (pageUsers.getRecords() != null) {
            TableUserDTO tableUserDTO = new TableUserDTO();
            pageUsers.getRecords().forEach(source -> {
                BeanUtils.copyProperties(source, tableUserDTO);
                tableUserDTOS.add(tableUserDTO);
            });
            tableUserDTOS.get(0).setTotal(pageUsers.getTotal());
        }

        return tableUserDTOS;
    }

    /**
     * 更新信息
     *
     * @param file
     * @param tableUser
     * @return
     */
    @PostMapping("/upDateUserMes")
    private String upDateUserMes(MultipartFile file, TableUser tableUser, HttpServletRequest request) {
        if (file != null && file.getSize() != 0) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String fileTyler = fileName.substring(fileName.lastIndexOf("."));
            long currentTimeMillis = System.currentTimeMillis();
            String parent = "User/" + tableUser.getUId();
            String child = currentTimeMillis + fileTyler;
            tableUser.setUPhoto(parent + "/" + child);

            File dest = new File(parent, child);

            String absolutePath = dest.getAbsolutePath();
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                //保存文件
                file.transferTo(Paths.get(absolutePath));
                boolean save = tableUserService.updateById(tableUser);
                assert save;
                return tableUser.getUPhoto();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return "false";
            }
        } else {
            boolean save = tableUserService.updateById(tableUser);
            assert save;
            request.getSession().setAttribute("user", tableUserService.getById(tableUser.getUId()));
            return "true";
        }
    }

    /**
     * 更新邮箱
     *
     * @param tableUser
     * @return
     */
    @PostMapping("/upDateEmail")
    private String upDateEmail(HttpServletRequest request, TableUser tableUser) {
        boolean b = tableUserService.updateById(tableUser);
        if (b) {
            request.getSession().setAttribute("user", tableUser);
            return tableUser.getUEmail();
        } else {
            return "2";
        }
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public boolean userRegister(TableUser user) {
        user.setUPwd(passwordEncryption(user.getUPwd()));
        return tableUserService.save(user);
    }

    /**
     * 更新密码
     *
     * @param request
     * @param uId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PostMapping("/UpDatePwd")
    private Integer upDatePwd(HttpServletRequest request, Integer uId, String oldPwd, String newPwd) {
        TableUser tableUser = tableUserService.getById(uId);
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(oldPwd, tableUser.getUPwd());
        if (!matches) {
            return 0;
        }
        String encodePwd = passwordEncoder.encode(newPwd);
        boolean b = tableUserService.upDatePwd(uId, encodePwd);
        if (b) {
            request.getSession().removeAttribute("user");
            return 1;
        } else {
            return 2;
        }
    }

    private String passwordEncryption(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //加密方法
        return encoder.encode(pwd);
    }
}