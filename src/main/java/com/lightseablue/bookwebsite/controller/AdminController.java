package com.lightseablue.bookwebsite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.*;
import com.lightseablue.bookwebsite.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: AdminController
 * @Package: com.lightseablue.bookwebsite.controller
 * @Description: 管理员跳转Controller
 * @author: LightseaBlue
 * @date: 2021/5/6     0:31
 */
@Controller
public class AdminController {
    @Resource
    TableUserService tableUserService;
    @Resource
    TableAllTypesService tableAllTypesService;
    @Resource
    TableAudioTypeService tableAudioTypeService;
    @Resource
    TableAudioNameService tableAudioNameService;
    @Resource
    TableAdminService tableAdminService;

    /**
     * 管理员主页
     *
     * @param request
     * @return
     */
    @RequestMapping("/Admin")
    private ModelAndView toAdmin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object user = request.getSession().getAttribute("admin");
        if (user == null) {
            modelAndView.setViewName("back/AdminLogin");
        } else {
            modelAndView.setViewName("back/index");
        }
        return modelAndView;
    }

    /**
     * 更新密码
     *
     * @param uId
     * @return
     */
    @GetMapping("/toUpdateAdminPwd")
    private ModelAndView toUpdateAdminPwd(String uId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("uId", uId);
        modelAndView.setViewName("back/upDateAdminPwd");
        return modelAndView;
    }

    /**
     * 管理员登出
     *
     * @param request
     * @return
     */
    @GetMapping("/adminLogout")
    private String adminLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "back/AdminLogin";
    }

    /**
     * 跳转管理员管理
     *
     * @return
     */
    @GetMapping("/toUpdateAdmin")
    private ModelAndView toUpdateAdmin() {
        Page<TableAdmin> tableAdmins = tableAdminService.getAdmins(1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admins", tableAdmins.getRecords());
        modelAndView.addObject("total", tableAdmins.getTotal());
        modelAndView.setViewName("back/UpdateAdmin");
        return modelAndView;
    }

    @GetMapping("/toAddAdmin")
    private String toAddAdmin() {
        return "back/addAdmin";
    }

    @GetMapping("/toUser")
    private ModelAndView toUser() {
        ModelAndView modelAndView = new ModelAndView();
        Page<TableUser> pageUsers = tableUserService.getPageUsers(1L, null, null);
        modelAndView.addObject("users", pageUsers.getRecords());
        modelAndView.addObject("total", pageUsers.getTotal());
        modelAndView.setViewName("back/UpdateUser");
        return modelAndView;
    }

    /**
     * 跳转图书管理
     *
     * @return
     */
    @GetMapping("/toCommodity")
    private ModelAndView toCommodity() {
        ModelAndView modelAndView = new ModelAndView();

        //分类类型
        List<TableAllTypes> allTypes = tableAllTypesService.list();
        modelAndView.addObject("allTypes", allTypes);
        //书籍类型
        List<TableAudioType> tableAudioTypes = tableAudioTypeService.list();
        modelAndView.addObject("tableAudioTypes", tableAudioTypes);

        //书籍列表
        Page<TableAudioName> page = new Page<>(1, 20);
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.page(page);
        List<TableAudioNameDTO> tableAudioNameDTOS = tableAudioNameService.pageToDto(tableAudioNamePage);
        modelAndView.addObject("tableAudioNameDTOS", tableAudioNameDTOS);
        modelAndView.addObject("total", tableAudioNamePage.getTotal());

        modelAndView.setViewName("back/Commodity");
        return modelAndView;
    }


}
