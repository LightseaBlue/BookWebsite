package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableUserDao;
import com.lightseablue.bookwebsite.entity.TableAdmin;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(TableUser)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:30
 */
@Service("tableUserService")
public class TableUserServiceImpl extends ServiceImpl<TableUserDao, TableUser> implements TableUserService {
    @Autowired
    TableUserDao tableUserDao;

    @Override
    public boolean upDateUserStu(Integer uId, Integer uStu) {
        UpdateWrapper<TableUser> tableUserUpdateWrapper = new UpdateWrapper<>();
        tableUserUpdateWrapper.lambda().eq(TableUser::getUId, uId).set(TableUser::getUStu, uStu);
        return this.update(tableUserUpdateWrapper);
    }

    @Override
    public Page<TableUser> getPageUsers(Long curr, String name, Integer stu) {
        Page<TableUser> tableUserPage = new Page<>(curr, 1);
        QueryWrapper<TableUser> tableUserQueryWrapper = new QueryWrapper<>();
        if (stu != null) {
            tableUserQueryWrapper.lambda().eq(TableUser::getUStu, stu);
        }
        if (name != null && !"".equals(name)) {
            tableUserQueryWrapper.lambda().like(TableUser::getUName, name);
        }
        return this.page(tableUserPage, tableUserQueryWrapper);
    }

    @Override
    public TableUser getUserByName(String userName) {
        QueryWrapper<TableUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TableUser::getUName, userName).eq(TableUser::getUStu, 1);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<TableUser> getUsersLikeUname(String uName) {
        QueryWrapper<TableUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TableUser::getUStu, 1).like(TableUser::getUName, uName);
        return this.list(queryWrapper);
    }

    @Override
    public boolean upDatePwd(Integer uId, String newPwd) {
        UpdateWrapper<TableUser> tableUserUpdateWrapper = new UpdateWrapper<>();
        tableUserUpdateWrapper.lambda().eq(TableUser::getUId, uId).set(TableUser::getUPwd, newPwd);
        return this.update(tableUserUpdateWrapper);
    }
}