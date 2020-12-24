package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableHistory;
import com.lightseablue.bookwebsite.service.TableHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 断点续听表(TableHistory)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@RestController
@RequestMapping("tableHistory")
public class TableHistoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableHistoryService tableHistoryService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param tableHistory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableHistory> page, TableHistory tableHistory) {
        return success(this.tableHistoryService.page(page, new QueryWrapper<>(tableHistory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableHistoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableHistory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableHistory tableHistory) {
        return success(this.tableHistoryService.save(tableHistory));
    }

    /**
     * 修改数据
     *
     * @param tableHistory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableHistory tableHistory) {
        return success(this.tableHistoryService.updateById(tableHistory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableHistoryService.removeByIds(idList));
    }
}