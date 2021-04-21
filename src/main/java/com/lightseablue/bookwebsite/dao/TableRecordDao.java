package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableRecord;

/**
 * 记录用户行为(TableRecord)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:39:06
 */
@Repository
public interface TableRecordDao extends BaseMapper<TableRecord> {

}
