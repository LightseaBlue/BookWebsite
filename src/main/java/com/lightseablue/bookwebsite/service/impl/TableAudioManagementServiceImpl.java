package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioManagementDao;
import com.lightseablue.bookwebsite.dto.PlayMusicDTO;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableHistory;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音频管理表      音频地址   (TableAudioManagement)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Service("tableAudioManagementService")
public class TableAudioManagementServiceImpl extends ServiceImpl<TableAudioManagementDao, TableAudioManagement> implements TableAudioManagementService {
    @Autowired
    TableAudioNameService tableAudioNameService;

    @Override
    public IPage<TableAudioManagement> findMusicList(String audioNameId, Integer thisPage) {
        Page<TableAudioManagement> page = new Page<>();
        page.setCurrent(thisPage);
        page.setSize(10);
        QueryWrapper<TableAudioManagement> managementQueryWrapper = new QueryWrapper<>();
        managementQueryWrapper.lambda().eq(TableAudioManagement::getAudioNameId, audioNameId).eq(TableAudioManagement::getAudioStu, 1);
        return this.page(page, managementQueryWrapper);
    }

    @Override
    public boolean updateStu(Integer audioId) {
        UpdateWrapper<TableAudioManagement> tableAudioManagementUpdateWrapper = new UpdateWrapper<>();
        tableAudioManagementUpdateWrapper.lambda().eq(TableAudioManagement::getAudioId, audioId).set(TableAudioManagement::getAudioStu, 2);
        return this.update(tableAudioManagementUpdateWrapper);
    }

    @Override
    public List<TableAudioManagement> findMusicListByAudioNameId(String audioNameId) {
        QueryWrapper<TableAudioManagement> managementQueryWrapper = new QueryWrapper<>();
        managementQueryWrapper.lambda().eq(TableAudioManagement::getAudioNameId, audioNameId).eq(TableAudioManagement::getAudioStu, 1);
        return this.list(managementQueryWrapper);
    }

    @Override
    public TableAudioManagement getAudioName(String audioNameId, Integer audioId) {
        QueryWrapper<TableAudioManagement> managementQueryWrapper = new QueryWrapper<>();
        managementQueryWrapper.lambda().eq(TableAudioManagement::getAudioNameId, audioNameId).eq(TableAudioManagement::getAudioId, audioId).eq(TableAudioManagement::getAudioStu, 1);
        return this.getOne(managementQueryWrapper);
    }

    @Override
    public PlayMusicDTO getSrcById(Integer audioId) {
        QueryWrapper<TableAudioManagement> tableAudioManagementQueryWrapper = new QueryWrapper<>();
        tableAudioManagementQueryWrapper.lambda().eq(TableAudioManagement::getAudioId, audioId).eq(TableAudioManagement::getAudioStu, 1);
        TableAudioManagement tableAudioManagement = this.getOne(tableAudioManagementQueryWrapper);
        return PlayMusicDTO.builder().audioId(tableAudioManagement.getAudioId())
                .audioAddress(tableAudioManagement.getAudioAddress())
                .audioName(tableAudioManagement.getAudioName())
                .audioNameId(tableAudioManagement.getAudioNameId())
                .audioTime(tableAudioManagement.getAudioUpdateTime()).build();
    }

    @Override
    public PlayMusicDTO findFirstSrc(String audioNameId) {
        QueryWrapper<TableAudioManagement> managementQueryWrapper = new QueryWrapper<>();
        managementQueryWrapper.lambda().eq(TableAudioManagement::getAudioNameId, audioNameId).eq(TableAudioManagement::getAudioStu, 1)
                .orderByAsc(TableAudioManagement::getAudioId)
                .last("limit 1");
        TableAudioManagement tableAudioManagement = this.getOne(managementQueryWrapper);
        QueryWrapper<TableAudioName> tableAudioNameQueryWrapper = new QueryWrapper<>();
        tableAudioNameQueryWrapper.lambda().eq(TableAudioName::getAudioNameId, audioNameId).eq(TableAudioName::getAudioNameStatus, 1);
        TableAudioName tableAudioName = tableAudioNameService.getOne(tableAudioNameQueryWrapper);
        return PlayMusicDTO.builder()
                .audioNameImg(tableAudioName.getAudioNameImg())
                .audioId(tableAudioManagement.getAudioId())
                .audioNameId(tableAudioName.getAudioNameId())
                .audioName(tableAudioManagement.getAudioName())
                .audioAddress(tableAudioManagement.getAudioAddress())
                .build();
    }
}