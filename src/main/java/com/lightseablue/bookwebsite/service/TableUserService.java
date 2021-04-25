package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.entity.TableUser;

import java.util.List;

/**
 * 用户表(TableUser)表服务接口
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:30
 */
public interface TableUserService extends IService<TableUser> {
    /**
     * 根据名字获取
     *
     * @param userName
     * @return
     */
    TableUser getUserByName(String userName);

    /**
     * 根据用户名模糊获取
     *
     * @param uName
     * @return
     */
    List<TableUser> getUsersLikeUname(String uName);

}