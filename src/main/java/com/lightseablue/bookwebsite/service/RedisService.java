package com.lightseablue.bookwebsite.service;

import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.entity.TableAudioType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @ClassName: RedisService
 * @Package: com.lightseablue.bookwebsite.service
 * @Description: Redis缓存操作
 * @author: LightseaBlue
 * @date: 2021/4/17     13:36
 */
@ApiModel("Redis操作服务类")
public interface RedisService {
    @ApiOperation("获取所有大类型")
    List<TableAllTypes> getAllTypes();

    @ApiOperation("获取所有小类型")
    Object getAllAudioTypes();

}
