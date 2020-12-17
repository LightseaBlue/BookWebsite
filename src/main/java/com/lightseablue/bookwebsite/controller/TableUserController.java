package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户表(TableUser)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:20
 */
@RestController
@RequestMapping("tableUser")
public class TableUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableUserService tableUserService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param tableUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableUser> page, TableUser tableUser) {
        return success(this.tableUserService.page(page, new QueryWrapper<>(tableUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableUser tableUser) {
        return success(this.tableUserService.save(tableUser));
    }

    /**
     * 修改数据
     *
     * @param tableUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableUser tableUser) {
        return success(this.tableUserService.updateById(tableUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableUserService.removeByIds(idList));
    }
}