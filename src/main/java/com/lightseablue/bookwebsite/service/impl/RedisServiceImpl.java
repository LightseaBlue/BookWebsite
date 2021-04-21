package com.lightseablue.bookwebsite.service.impl;

import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.entity.TableAudioType;
import com.lightseablue.bookwebsite.service.RedisService;
import com.lightseablue.bookwebsite.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RedisServiceImpl
 * @Package: com.lightseablue.bookwebsite.service.impl
 * @Description: Redis缓存操作实现类
 * @author: LightseaBlue
 * @date: 2021/4/17     13:37
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final String key = "bookWebSite";
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<TableAllTypes> getAllTypes() {
        String allTypes = "allTypes";
        return (List<TableAllTypes>) getValue(allTypes);
    }

    @Override
    public List<List<TableAudioType>> getAllAudioTypes() {
        String allAudioTypes = "allAudioTypes";
        return (List<List<TableAudioType>>) getValue(allAudioTypes);
    }

    /**
     * 返回数据
     *
     * @param item
     * @return
     */
    private Object getValue(String item) {
        boolean isExist = redisUtil.hHasKey(key, item);
        if (isExist) {
            return redisUtil.hget(key, item);
        }
        return null;
    }
}
