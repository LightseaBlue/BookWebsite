package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableAudioType;

/**
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:39:05
 */
@Repository
public interface TableAudioTypeDao extends BaseMapper<TableAudioType> {

}
