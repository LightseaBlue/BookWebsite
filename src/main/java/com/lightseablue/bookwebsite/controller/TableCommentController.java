package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.lightseablue.bookwebsite.service.TableCommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 评论表(TableComment)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@RestController
@RequestMapping("tableComment")
public class TableCommentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableCommentService tableCommentService;


}