package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioNameDao;
import com.lightseablue.bookwebsite.dao.TableAudioTypeDao;
import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.entity.TableAudioType;
import com.lightseablue.bookwebsite.service.TableAudioTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:28
 */
@Service("tableAudioTypeService")
public class TableAudioTypeServiceImpl extends ServiceImpl<TableAudioTypeDao, TableAudioType> implements TableAudioTypeService {
    @Autowired
    TableAudioTypeDao dao;

    @Override
    public List<TableAudioType> getTableAudioTypes(Integer allTypeId) {
        QueryWrapper<TableAudioType> audioTypeWrapper = new QueryWrapper<>();
        audioTypeWrapper.lambda().eq(TableAudioType::getAllTypeId, allTypeId);
        return this.list(audioTypeWrapper);
    }
}