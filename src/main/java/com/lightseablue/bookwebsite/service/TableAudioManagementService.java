package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.dto.PlayMusicDTO;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;

import java.util.List;

/**
 * 音频管理表      音频地址   (TableAudioManagement)表服务接口
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
public interface TableAudioManagementService extends IService<TableAudioManagement> {

    /**
     * 查找播放列表
     *
     * @param audioNameId
     * @param thisPage    当前页数
     * @return
     */
    IPage<TableAudioManagement> findMusicList(String audioNameId, Integer thisPage);

    /**
     * 目的该条id记录是否有数据返回
     *
     * @param audioNameId
     * @param audioId
     * @return
     */
    TableAudioManagement getAudioName(String audioNameId, Integer audioId);

    /**
     * 根据id查找音频信息
     *
     * @param audioId
     * @return
     */
    PlayMusicDTO getSrcById(Integer audioId);

    /**
     * 第一次点击新的书籍播放音频
     *
     * @param audioNameId
     * @return
     */
    PlayMusicDTO findFirstSrc(String audioNameId);
}