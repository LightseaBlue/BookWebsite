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
 * @since 2020-12-16 19:28:10
 */
@RestController
@RequestMapping("tableAudioType")
public class TableAudioTypeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAudioTypeService tableAudioTypeService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param tableAudioType 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableAudioType> page, TableAudioType tableAudioType) {
        return success(this.tableAudioTypeService.page(page, new QueryWrapper<>(tableAudioType)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableAudioTypeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableAudioType 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableAudioType tableAudioType) {
        return success(this.tableAudioTypeService.save(tableAudioType));
    }

    /**
     * 修改数据
     *
     * @param tableAudioType 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableAudioType tableAudioType) {
        return success(this.tableAudioTypeService.updateById(tableAudioType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableAudioTypeService.removeByIds(idList));
    }
}