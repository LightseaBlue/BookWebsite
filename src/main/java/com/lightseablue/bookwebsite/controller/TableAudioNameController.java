package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:08
 */
@RestController
@RequestMapping("tableAudioName")
public class TableAudioNameController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAudioNameService tableAudioNameService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param tableAudioName 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableAudioName> page, TableAudioName tableAudioName) {
        return success(this.tableAudioNameService.page(page, new QueryWrapper<>(tableAudioName)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableAudioNameService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableAudioName 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableAudioName tableAudioName) {
        return success(this.tableAudioNameService.save(tableAudioName));
    }

    /**
     * 修改数据
     *
     * @param tableAudioName 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableAudioName tableAudioName) {
        return success(this.tableAudioNameService.updateById(tableAudioName));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableAudioNameService.removeByIds(idList));
    }
}