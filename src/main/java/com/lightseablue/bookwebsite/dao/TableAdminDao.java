package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableAdmin;

/**
 * 管理员(TableAdmin)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:33
 */
@Repository
public interface TableAdminDao extends BaseMapper<TableAdmin> {

}
