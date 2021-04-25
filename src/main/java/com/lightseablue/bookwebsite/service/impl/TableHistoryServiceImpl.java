package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableHistoryDao;
import com.lightseablue.bookwebsite.dto.PlayMusicDTO;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableHistory;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 断点续听表(TableHistory)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@Service("tableHistoryService")
public class TableHistoryServiceImpl extends ServiceImpl<TableHistoryDao, TableHistory> implements TableHistoryService {
    @Autowired
    TableAudioNameService tableAudioNameService;
    @Autowired
    TableAudioManagementService tableAudioManagementService;

    @Override
    public PlayMusicDTO findUserFirstSrc(String audioNameId, Integer uid) {
        QueryWrapper<TableHistory> managementQueryWrapper = new QueryWrapper<>();
        managementQueryWrapper.lambda().eq(TableHistory::getAudioNameId, audioNameId)
                .eq(TableHistory::getUId, uid);
        TableHistory tableHistory = this.getOne(managementQueryWrapper);
        if (tableHistory == null) {
            return null;
        }
        QueryWrapper<TableAudioName> tableAudioNameQueryWrapper = new QueryWrapper<>();
        tableAudioNameQueryWrapper.lambda().eq(TableAudioName::getAudioNameId, tableHistory.getAudioNameId()).eq(TableAudioName::getAudioNameStatus, 1);
        TableAudioName tableAudioName = tableAudioNameService.getOne(tableAudioNameQueryWrapper);
        QueryWrapper<TableAudioManagement> tableAudioManagementQueryWrapper = new QueryWrapper<>();
        tableAudioManagementQueryWrapper.lambda().eq(TableAudioManagement::getAudioId, tableHistory.getAudioId()).eq(TableAudioManagement::getAudioStu, 1);
        TableAudioManagement audioManagement = tableAudioManagementService.getOne(tableAudioManagementQueryWrapper);
        return PlayMusicDTO.builder()
                .audioNameImg(tableAudioName.getAudioNameImg())
                .audioAddress(audioManagement.getAudioAddress())
                .audioName(audioManagement.getAudioName())
                .audioNameId(tableAudioName.getAudioNameId())
                .audioId(audioManagement.getAudioId())
                .audioTypeId(tableAudioName.getAudioTypeId())
                .build();
    }
}