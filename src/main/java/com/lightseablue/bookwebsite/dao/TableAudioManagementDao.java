package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;

/**
 * 音频管理表      音频地址   (TableAudioManagement)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:39:04
 */
@Repository
public interface TableAudioManagementDao extends BaseMapper<TableAudioManagement> {

}
