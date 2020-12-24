package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableRecord;
import com.lightseablue.bookwebsite.service.TableRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 记录用户行为(TableRecord)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@RestController
@RequestMapping("tableRecord")
public class TableRecordController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableRecordService tableRecordService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param tableRecord 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableRecord> page, TableRecord tableRecord) {
        return success(this.tableRecordService.page(page, new QueryWrapper<>(tableRecord)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableRecordService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableRecord 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableRecord tableRecord) {
        return success(this.tableRecordService.save(tableRecord));
    }

    /**
     * 修改数据
     *
     * @param tableRecord 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableRecord tableRecord) {
        return success(this.tableRecordService.updateById(tableRecord));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableRecordService.removeByIds(idList));
    }
}