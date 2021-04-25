package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableRecordDao;
import com.lightseablue.bookwebsite.entity.TableRecord;
import com.lightseablue.bookwebsite.service.TableRecordService;
import org.springframework.stereotype.Service;

/**
 * 记录用户行为(TableRecord)表服务实现类
 *
 * @author makejava
 * @since 2021-04-17 15:46:25
 */
@Service("tableRecordService")
public class TableRecordServiceImpl extends ServiceImpl<TableRecordDao, TableRecord> implements TableRecordService {

    @Override
    public TableRecord getUser(Integer uid, Integer audioTypeId) {
        QueryWrapper<TableRecord> tableRecordQueryWrapper = new QueryWrapper<>();
        tableRecordQueryWrapper.lambda().eq(TableRecord::getUId, uid).eq(TableRecord::getAudioTypeId, audioTypeId);
        return this.getOne(tableRecordQueryWrapper);
    }
}
