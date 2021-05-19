package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.lightseablue.bookwebsite.service.TableRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 记录用户行为(TableRecord)表控制层
 *
 * @author makejava
 * @since 2021-04-17 15:46:25
 */
@RestController
@RequestMapping("tableRecord")
public class TableRecordController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableRecordService tableRecordService;
}
