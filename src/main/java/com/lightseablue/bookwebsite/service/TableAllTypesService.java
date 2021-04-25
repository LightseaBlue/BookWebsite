package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.entity.TableAllTypes;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes)表服务接口
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
public interface TableAllTypesService extends IService<TableAllTypes> {

    public List<TableAllTypes> getAll();
}