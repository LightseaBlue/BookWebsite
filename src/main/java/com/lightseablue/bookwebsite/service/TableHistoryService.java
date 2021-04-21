package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.dto.PlayMusicDTO;
import com.lightseablue.bookwebsite.entity.TableHistory;

/**
 * 断点续听表(TableHistory)表服务接口
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
public interface TableHistoryService extends IService<TableHistory> {
    /**
     * 查询用户历史表   断点续听
     *
     * @param audioNameId
     * @param uid
     * @return
     */
    PlayMusicDTO findUserFirstSrc(String audioNameId, Integer uid);
}