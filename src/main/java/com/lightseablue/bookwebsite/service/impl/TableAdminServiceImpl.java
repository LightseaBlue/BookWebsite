package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAdminDao;
import com.lightseablue.bookwebsite.entity.TableAdmin;
import com.lightseablue.bookwebsite.service.TableAdminService;
import org.springframework.stereotype.Service;

/**
 * 管理员(TableAdmin)表服务实现类
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:34
 */
@Service("tableAdminService")
public class TableAdminServiceImpl extends ServiceImpl<TableAdminDao, TableAdmin> implements TableAdminService {

    @Override
    public boolean upDateAdminStuById(Integer aid, Integer aStu) {
        UpdateWrapper<TableAdmin> tableAdminUpdateWrapper = new UpdateWrapper<>();
        tableAdminUpdateWrapper.lambda().eq(TableAdmin::getAId, aid).set(TableAdmin::getAStu, aStu);
        return this.update(tableAdminUpdateWrapper);
    }

    @Override
    public Page<TableAdmin> conditionSearchAdmin(String name, Integer Astu, Integer curr) {
        Page<TableAdmin> page = setPage(curr);
        QueryWrapper<TableAdmin> queryWrapper = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            queryWrapper.lambda().like(TableAdmin::getAName, name);
        }
        if (Astu != null) {
            queryWrapper.lambda().eq(TableAdmin::getAStu, Astu);
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<TableAdmin> getAdmins(Integer curr) {
        Page<TableAdmin> page = setPage(curr);
        return this.page(page);
    }

    private Page<TableAdmin> setPage(Integer curr) {
        Page<TableAdmin> page = new Page<>();
        page.setSize(1);
        page.setCurrent(curr);
        return page;
    }

    @Override
    public TableAdmin verifyLogin(String adminName, String pwd) {
        QueryWrapper<TableAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TableAdmin::getAName, adminName)
                .eq(TableAdmin::getAPwd, pwd)
                .lt(TableAdmin::getAStu, 3);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean isAdmin(Integer aId, String pwd) {
        QueryWrapper<TableAdmin> tableAdminQueryWrapper = new QueryWrapper<>();
        tableAdminQueryWrapper.lambda().eq(TableAdmin::getAId, aId).eq(TableAdmin::getAPwd, pwd);
        TableAdmin tableAdmin = this.getOne(tableAdminQueryWrapper);
        if (tableAdmin == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean upDateAdminPwd(Integer aId, String newPwd) {
        UpdateWrapper<TableAdmin> tableAdminUpdateWrapper = new UpdateWrapper<>();
        tableAdminUpdateWrapper.lambda().eq(TableAdmin::getAId, aId).set(TableAdmin::getAPwd, newPwd);
        return this.update(tableAdminUpdateWrapper);
    }
}
