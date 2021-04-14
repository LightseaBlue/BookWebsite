package com.lightseablue.bookwebsite.reptile;

import com.lightseablue.bookwebsite.entity.TableUser;
import io.swagger.annotations.ApiOperation;

/**
 * @Program: bookwebsite
 * @ClassName: Reptile
 * @Author: LightseaBlue
 * @Date: 2021-02-03 11:31
 * @Version: V1.0
 */
public interface Reptile {
    @ApiOperation("喜马拉雅爬虫")
    void xmlyReptile(String name, TableUser tableUser, int audioTypeId);
}
