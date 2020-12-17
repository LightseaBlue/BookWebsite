package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.entity.TableComment;
import com.lightseablue.bookwebsite.service.TableCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 评论表(TableComment)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:13
 */
@RestController
@RequestMapping("tableComment")
public class TableCommentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableCommentService tableCommentService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param tableComment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableComment> page, TableComment tableComment) {
        return success(this.tableCommentService.page(page, new QueryWrapper<>(tableComment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableCommentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableComment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableComment tableComment) {
        return success(this.tableCommentService.save(tableComment));
    }

    /**
     * 修改数据
     *
     * @param tableComment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableComment tableComment) {
        return success(this.tableCommentService.updateById(tableComment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableCommentService.removeByIds(idList));
    }
}