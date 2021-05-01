package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableAudioType;
import com.lightseablue.bookwebsite.service.TableAudioTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:28
 */
@RestController
@RequestMapping("/tableAudioType")
public class TableAudioTypeController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private TableAudioTypeService tableAudioTypeService;

    @PostMapping("/getAudioTypeByAllTypeId")
    private List<TableAudioType> getAudioTypeByAllTypeId(Integer allTypeId) {
        return tableAudioTypeService.getTableAudioTypes(allTypeId);
    }
}