package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableUserDao;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(TableUser)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:20
 */
@Service("tableUserService")
public class TableUserServiceImpl extends ServiceImpl<TableUserDao, TableUser> implements TableUserService {

}