package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.entity.TableRecord;

/**
 * 记录用户行为(TableRecord)表服务接口
 *
 * @author makejava
 * @since 2021-04-17 15:46:24
 */
public interface TableRecordService extends IService<TableRecord> {
    TableRecord getUser(Integer uid, Integer audioTypeId);
}
