package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableErrorRecordsDao;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;
import com.lightseablue.bookwebsite.service.TableErrorRecordsService;
import org.springframework.stereotype.Service;

/**
 * 爬取出错(TableErrorRecords)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@Service("tableErrorRecordsService")
public class TableErrorRecordsServiceImpl extends ServiceImpl<TableErrorRecordsDao, TableErrorRecords> implements TableErrorRecordsService {

}