package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;

/**
 * 爬取出错(TableErrorRecords)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-17 22:53:24
 */
@Repository
public interface TableErrorRecordsDao extends BaseMapper<TableErrorRecords> {

}
