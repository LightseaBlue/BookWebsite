package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAdminDTO;
import com.lightseablue.bookwebsite.entity.TableAdmin;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.service.TableAdminService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import org.springframework.beans.BeanUtils;
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
     * 添加管理员
     *
     * @param file
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    private boolean addAdmin(MultipartFile file, TableAdmin admin) {
        assert file.isEmpty();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileTyler = fileName.substring(fileName.lastIndexOf("."));
        long currentTimeMillis = System.currentTimeMillis();
        String parent = "Admin/" + currentTimeMillis;
        String child = currentTimeMillis + fileTyler;
        admin.setAImg(parent + "/" + child);

        File dest = new File(parent, child);
        String absolutePath = dest.getAbsolutePath();
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(Paths.get(absolutePath));
            return tableAdminService.save(admin);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改管理员权限
     *
     * @param aId
     * @param aStu
     * @return
     */
    @PostMapping("/upDateAdminStu")
    private boolean upDateAdminStu(Integer aId, Integer aStu) {
        return tableAdminService.upDateAdminStuById(aId, aStu);
    }

    /**
     * 条件分页
     *
     * @param adminName
     * @param adminStu
     * @param curr
     * @return
     */
    @PostMapping("/conditionSearchAdmin")
    private List<TableAdminDTO> conditionSearchAdmin(HttpServletRequest model, String adminName, Integer adminStu, Integer curr) {
        Page<TableAdmin> page = tableAdminService.conditionSearchAdmin(adminName, adminStu, curr);
        List<TableAdminDTO> tableAdmins = new ArrayList<>();
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            TableAdminDTO target = new TableAdminDTO();
            page.getRecords().forEach(source -> {
                BeanUtils.copyProperties(source, target);
                tableAdmins.add(target);
            });

            tableAdmins.get(0).setTotal(page.getTotal());
        }
        return tableAdmins;
    }

    /**
     * 无条件分页
     *
     * @param curr
     * @return
     */
    @PostMapping("/switchAdminNumber")
    private List<TableAdmin> switchAdminNumber(Integer curr) {
        return tableAdminService.getAdmins(curr).getRecords();
    }

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

    /**
     * 更新管理员密码
     *
     * @param oldPwd
     * @param newPwd
     * @param aId
     * @return
     */
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
