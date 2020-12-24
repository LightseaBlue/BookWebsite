package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;
import com.lightseablue.bookwebsite.service.TableErrorRecordsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 爬取出错(TableErrorRecords)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@RestController
@RequestMapping("tableErrorRecords")
public class TableErrorRecordsController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableErrorRecordsService tableErrorRecordsService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param tableErrorRecords 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableErrorRecords> page, TableErrorRecords tableErrorRecords) {
        return success(this.tableErrorRecordsService.page(page, new QueryWrapper<>(tableErrorRecords)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableErrorRecordsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableErrorRecords 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableErrorRecords tableErrorRecords) {
        return success(this.tableErrorRecordsService.save(tableErrorRecords));
    }

    /**
     * 修改数据
     *
     * @param tableErrorRecords 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableErrorRecords tableErrorRecords) {
        return success(this.tableErrorRecordsService.updateById(tableErrorRecords));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableErrorRecordsService.removeByIds(idList));
    }
}