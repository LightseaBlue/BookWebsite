package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableUser;

import java.util.List;

/**
 * 用户表(TableUser)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-17 22:53:24
 */
@Repository
public interface TableUserDao extends BaseMapper<TableUser> {

    /**
     * todo: sql很瞎
     */
    @Select("select * from table_audio_name where AUDIO_NAME_NAME like '%#{uName}%'")
    List<TableUser> getUsersLikeUname(String uName);
}
