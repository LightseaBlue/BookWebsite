package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableComment;

/**
 * 评论表(TableComment)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-17 22:53:24
 */
@Repository
public interface TableCommentDao extends BaseMapper<TableComment> {

}
