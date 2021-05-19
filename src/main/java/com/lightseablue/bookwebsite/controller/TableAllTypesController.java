package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.lightseablue.bookwebsite.service.TableAllTypesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@RestController
@RequestMapping("tableAllTypes")
public class TableAllTypesController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAllTypesService tableAllTypesService;

}