package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.entity.TableAdmin;

/**
 * 管理员(TableAdmin)表服务接口
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:33
 */
public interface TableAdminService extends IService<TableAdmin> {

    /**
     * 更新管理员状态
     *
     * @param aid
     * @param aStu
     * @return
     */
    boolean upDateAdminStuById(Integer aid, Integer aStu);

    /**
     * 条件查找管理员
     *
     * @param name
     * @param Astu
     * @param curr
     * @return
     */
    Page<TableAdmin> conditionSearchAdmin(String name, Integer Astu, Integer curr);

    /**
     * 无条件分页查询管理员
     *
     * @param curr
     * @return
     */
    Page<TableAdmin> getAdmins(Integer curr);

    /**
     * 验证登陆
     *
     * @param adminName
     * @param pwd
     * @return
     */
    TableAdmin verifyLogin(String adminName, String pwd);

    /**
     * 验证管理员密码是否正确
     *
     * @param aId
     * @param pwd
     * @return
     */
    boolean isAdmin(Integer aId, String pwd);

    /**
     * 更新管理员密码
     *
     * @param aId
     * @param newPwd
     * @return
     */
    boolean upDateAdminPwd(Integer aId, String newPwd);
}
