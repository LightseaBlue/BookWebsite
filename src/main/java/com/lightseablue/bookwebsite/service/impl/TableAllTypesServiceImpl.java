package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAllTypesDao;
import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.service.TableAllTypesService;
import org.springframework.stereotype.Service;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Service("tableAllTypesService")
public class TableAllTypesServiceImpl extends ServiceImpl<TableAllTypesDao, TableAllTypes> implements TableAllTypesService {

}