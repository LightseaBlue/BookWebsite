package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableAllTypesService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableAudioTypeService;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@RestController
@RequestMapping("/tableAudioName")
public class TableAudioNameController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAudioNameService tableAudioNameService;
    @Resource
    private TableAudioTypeService tableAudioTypeService;
    @Resource
    private TableAllTypesService tableAllTypesService;
    @Resource
    private TableUserService tableUserService;

    @PostMapping("/adminFindBooks")
    private List<TableAudioNameDTO> adminFindBooks(String name, String allTypeId, String typeId, Integer stu, Integer thisPage) {
        //模糊查找用户名
        List<Integer> allUid = null;
        if (name != null && !"".equals(name.trim())) {
            List<TableUser> tableUsers = tableUserService.getUsersLikeUname(name);
            if (tableUsers != null && tableUsers.size() > 0) {
                allUid = new ArrayList<>();
                for (TableUser tableAudioName : tableUsers) {
                    allUid.add(tableAudioName.getUId());
                }
            }
        }

        //书籍名
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.searchLikeBooks(name, thisPage, 20, allUid, allTypeId, typeId, stu);
        List<TableAudioNameDTO> tableAudioNameDTOS = tableAudioNameService.pageToDto(tableAudioNamePage);

        return tableAudioNameDTOS;
    }

    /**
     * 管理员更新书籍
     *
     * @param audioNameId
     * @param audioNameStatus
     * @return
     */
    @PostMapping("/upDataBookStu")
    private boolean upDataBookStu(String audioNameId, String audioNameStatus) {
        if ("1".equals(audioNameStatus)) {
            return tableAudioNameService.updateByAudioNameId(audioNameId);
        } else {
            return tableAudioNameService.updateByAudioNameIdToOk(audioNameId);
        }
    }

    /**
     * 管理员端无条件切换页数
     *
     * @param current
     * @return
     */
    @PostMapping("/switchPageNumber")
    private List<TableAudioNameDTO> switchPageNumber(int current) {
        Page<TableAudioName> page = new Page<>(current, 20);
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.page(page);
        List<TableAudioNameDTO> tableAudioNameDTOS = tableAudioNameService.pageToDto(tableAudioNamePage);
        return tableAudioNameDTOS;
    }


    /**
     * 创建图书
     *
     * @param file
     * @param tableAudioName
     * @return
     */
    @PostMapping("/upDateBook")
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
        Object thisNumObject = request.getSession().getAttribute("thisNum");
        int thisNum = 1;
        if (thisNumObject != null) {
            thisNum = Integer.parseInt(thisNumObject.toString());
        }
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
}