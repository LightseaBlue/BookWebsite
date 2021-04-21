package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableUserDao;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(TableUser)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:30
 */
@Service("tableUserService")
public class TableUserServiceImpl extends ServiceImpl<TableUserDao, TableUser> implements TableUserService {

}