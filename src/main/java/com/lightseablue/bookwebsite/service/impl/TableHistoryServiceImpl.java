package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableHistoryDao;
import com.lightseablue.bookwebsite.entity.TableHistory;
import com.lightseablue.bookwebsite.service.TableHistoryService;
import org.springframework.stereotype.Service;

/**
 * 断点续听表(TableHistory)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@Service("tableHistoryService")
public class TableHistoryServiceImpl extends ServiceImpl<TableHistoryDao, TableHistory> implements TableHistoryService {

}