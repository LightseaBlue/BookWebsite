package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableAllTypesService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableAudioTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Controller
@RequestMapping("/tableAudioName")
public class TableAudioNameController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAudioNameService tableAudioNameService;

    @PostMapping("/upDateBook")
    @ResponseBody
    private String createBook(@RequestParam("file") MultipartFile file, TableAudioName tableAudioName) {
        assert file.isEmpty();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileTyler = fileName.substring(fileName.lastIndexOf("."));
        long currentTimeMillis = System.currentTimeMillis();
        String parent = "Audio/" + currentTimeMillis;
        String child = currentTimeMillis + fileTyler;
        tableAudioName.setAudioNameDate(new Date());
        tableAudioName.setAudioNameImg(parent + "/" + child);
        tableAudioName.setAudioNameId(String.valueOf(currentTimeMillis));

        File dest = new File(parent, child);

        String absolutePath = dest.getAbsolutePath();
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(Paths.get(absolutePath));
            boolean save = tableAudioNameService.save(tableAudioName);
            assert save;
            return tableAudioName.getAudioNameId();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
    /**
     * 切换你的喜欢
     *
     * @param request
     * @return
     */
    @GetMapping("/changeYouLike")
    public List<TableAudioNameDTO> changeYouLike(HttpServletRequest request) {
        int thisNum = Integer.parseInt(request.getSession().getAttribute("thisNum").toString());
        //查找全部排行榜的页数
        Object thisNum1 = request.getSession().getAttribute("allTopBooksNum");
        int allTopBooksNum = 1;
        if (thisNum1 != null && Integer.parseInt(thisNum1.toString()) <= 5) {
            allTopBooksNum = Integer.parseInt(thisNum1.toString());
        }

        if (thisNum <= 8) {
            thisNum++;
        } else {
            thisNum = 1;
            allTopBooksNum = 1;
        }
        TableUser user = (TableUser) request.getSession().getAttribute("user");
        List<TableAudioNameDTO> youLike;

        if (user != null) {
            youLike = tableAudioNameService.getYouLike(user.getUId(), thisNum);
            if (youLike == null) {
                youLike = tableAudioNameService.findAllTopBook(thisNum);
            }
        } else {
            youLike = tableAudioNameService.findAllTopBook(thisNum);
        }

        if (youLike.size() < 3) {
            if (youLike.size() == 0) {
                youLike = new ArrayList<>();
            }
            for (int j = youLike.size(); j < 3; j++) {
                List<TableAudioNameDTO> allTopBook = tableAudioNameService.findAllTopBook(allTopBooksNum);
                TableAudioNameDTO tableAudioNameDTO = allTopBook.get(j);
                youLike.add(tableAudioNameDTO);
            }
            allTopBooksNum++;
        }
        //喜欢轮数
        request.getSession().setAttribute("thisNum", thisNum);
        //所有排行榜轮数
        request.getSession().setAttribute("allTopBooksNum", allTopBooksNum);
        return youLike;
    }

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