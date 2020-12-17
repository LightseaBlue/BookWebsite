package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableRecordDao;
import com.lightseablue.bookwebsite.entity.TableRecord;
import com.lightseablue.bookwebsite.service.TableRecordService;
import org.springframework.stereotype.Service;

/**
 * 记录用户行为(TableRecord)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:17
 */
@Service("tableRecordService")
public class TableRecordServiceImpl extends ServiceImpl<TableRecordDao, TableRecord> implements TableRecordService {

}