package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableAllTypes;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:39:04
 */
@Repository
public interface TableAllTypesDao extends BaseMapper<TableAllTypes> {

}
