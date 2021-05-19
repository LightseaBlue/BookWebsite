package com.lightseablue.bookwebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.*;
import com.lightseablue.bookwebsite.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: bookwebsite
 * @ClassName: IndexController
 * @Author: LightseaBlue
 * @Date: 2020-12-18 21:01
 * @Version: V1.0
 */
@Controller
@Api("主页控制器")
public class IndexController {
    @Autowired
    TableAllTypesService tableAllTypesService;
    @Autowired
    TableAudioTypeService tableAudioTypeService;
    @Autowired
    TableAudioNameService tableAudioNameService;
    @Autowired
    TableAudioManagementService tableAudioManagementService;
    @Autowired
    RedisService redisService;
    @Autowired
    TableUserService tableUserService;

    @ApiOperation("主页加载")
    @RequestMapping({"/", "/index"})
    public ModelAndView toIndex(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        // 所有大类型和小类型
        List<TableAllTypes> allTypes = tableAllTypesService.getAll();
        List<List<TableAudioType>> allAudioTypes = new ArrayList<>();
        if (allTypes != null) {
            for (int i = 0; i < 4; i++) {
                allAudioTypes.add(tableAudioTypeService.getTableAudioTypes(allTypes.get(i).getAllTypeId()));
            }
        }
        // 喜欢或者推荐
        HttpSession session = request.getSession();
        TableUser user = (TableUser) session.getAttribute("user");
        List<TableAudioNameDTO> youLikesOrTops;
        if (user != null) {
            youLikesOrTops = tableAudioNameService.getYouLike(user.getUId(), 1);
            if (youLikesOrTops == null) {
                youLikesOrTops = tableAudioNameService.findAllTopBook(1);
            }
        } else {
            youLikesOrTops = tableAudioNameService.findAllTopBook(1);
        }
        // 所有大类型的排行榜
        List<List<TableAudioNameDTO>> allTopBooks = new ArrayList<>();
        // 所有展示书籍
        List<List<TableAudioNameDTO>> allBooks = new ArrayList<>();
        assert allTypes != null;
        for (TableAllTypes b : allTypes) {
            QueryWrapper<TableAudioName> tableAudioNameQueryWrapper = new QueryWrapper<>();
            tableAudioNameQueryWrapper.lambda().eq(TableAudioName::getAllTypeId, b.getAllTypeId());
            allTopBooks.add(tableAudioNameService.searchTopBookByType(b.getAllTypeId()));
            allBooks.add(tableAudioNameService.searchBooksByType(b.getAllTypeId()));
        }

        //所有大类型和小类型
        modelAndView.addObject("bigTypes", allTypes);
        request.getSession().setAttribute("bigTypes", allTypes);
        modelAndView.addObject("allTypes", allAudioTypes);
        //推荐喜欢或者排行榜
        modelAndView.addObject("youLikesOrTops", youLikesOrTops);
        modelAndView.addObject("allTopBooks", allTopBooks);
        //每一个大类型中最新的5本书
        modelAndView.addObject("allBooks", allBooks);
        //猜你喜欢的页数
        session.setAttribute("thisNum", 1);

        modelAndView.setViewName("view/index");
        return modelAndView;
    }

    /**
     * 跳转类型展示界面
     *
     * @return
     */
    @GetMapping("/booksList")
    public ModelAndView toBooksList(Integer allTypeId, Integer thisPage, String allTypeName) {
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.searchAllBooksByType(allTypeId, thisPage);
        ModelAndView modelAndView = setBookListResParam(tableAudioNamePage, thisPage, allTypeName);
        modelAndView.setViewName("view/booksList");
        return modelAndView;
    }

    /**
     * 跳转小类型展示界面
     *
     * @param audioTypeId
     * @param thisPage
     * @param allTypeName
     * @return
     */
    @GetMapping("/bookTypeList")
    public ModelAndView toBooksNameList(Integer audioTypeId, Integer thisPage, String allTypeName) {
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.searchAllBooksByAudioTypeId(audioTypeId, thisPage);
        ModelAndView modelAndView = setBookListResParam(tableAudioNamePage, thisPage, allTypeName);
        modelAndView.setViewName("view/booksList");
        return modelAndView;
    }

    private ModelAndView setBookListResParam(IPage<TableAudioName> tableAudioNamePage, Integer thisPage, String allTypeName) {
        ModelAndView modelAndView = new ModelAndView();
        if (tableAudioNamePage.getTotal() == 0) {
            modelAndView.addObject("thisPage", 1);
            return modelAndView;
        }
        List<TableAudioNameDTO> tableAudioNameDtoS = tableAudioNameService.pageToList(tableAudioNamePage);
        modelAndView.addObject("bookLists", tableAudioNameDtoS);
        modelAndView.addObject("thisPage", thisPage + 1);
        modelAndView.addObject("allPages", tableAudioNamePage.getPages());
        modelAndView.addObject("allBookNum", tableAudioNamePage.getTotal());
        modelAndView.addObject("allTypeName", allTypeName);
        modelAndView.addObject("allTypeId", tableAudioNameDtoS.get(0).getAllTypeId());
        modelAndView.addObject("prePage", thisPage - 1);
        return modelAndView;
    }

    /**
     * 通过书籍名或者作者名模糊查询 跳转查询结果
     *
     * @return
     */
    @GetMapping("/search")
    public ModelAndView searchBooks(String searchText, Integer thisPage) {
        ModelAndView modelAndView;
        //作者名
        List<TableUser> tableUsers = tableUserService.getUsersLikeUname(searchText);
        List<Integer> allUid = null;
        if (tableUsers != null && tableUsers.size() > 0) {
            allUid = new ArrayList<>();
            for (TableUser tableAudioName : tableUsers) {
                allUid.add(tableAudioName.getUId());
            }
        }
        //书籍名
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.searchLikeBooks(searchText, thisPage, 6, allUid, null, null, 1);
        modelAndView = setBookListResParam(tableAudioNamePage, thisPage, null);
        modelAndView.addObject("searchText", searchText);
        modelAndView.setViewName("view/SearchBooks");
        return modelAndView;
    }

    /**
     * 跳转修改用户信息
     *
     * @param uId
     * @return
     */
    @GetMapping("/toUpDateUser")
    private ModelAndView toUpDateUser(Integer uId) {
        ModelAndView modelAndView = new ModelAndView();
        TableUser tableUser = tableUserService.getById(uId);
        if (tableUser.getUPhoto() == null) {
            tableUser.setUPhoto("img/Triple.png");
        }
        if (tableUser.getUEmail() == null || "".equals(tableUser.getUEmail())) {
            tableUser.setUEmail("无");
        }
        modelAndView.addObject("user", tableUser);
        modelAndView.setViewName("view/UpdateUser");
        return modelAndView;
    }

    /**
     * 跳转密码修改
     *
     * @return
     */
    @GetMapping("/toUpDateUserPwd")
    private ModelAndView toUpDateUserPwd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/UpdateUserPwd");
        return modelAndView;
    }

    /**
     * 跳转我的书籍主页
     *
     * @param uid
     * @param thisPage
     * @return
     */
    @GetMapping("/upLoad")
    public ModelAndView toUpLoad(Integer uid, Integer thisPage) {
        Page<TableAudioName> tableAudioNamePage = tableAudioNameService.searchBooksByUid(uid, thisPage);
        ModelAndView modelAndView = setBookListResParam(tableAudioNamePage, thisPage, null);
        modelAndView.addObject("uid", uid);
        modelAndView.setViewName("view/upLoad");
        return modelAndView;
    }

    @GetMapping("/login")
    public String toLogin() {
        return "view/login";
    }

    /**
     * 跳转意见上传
     *
     * @return
     */
    @GetMapping("/contactTheAdministrator")
    public ModelAndView toContactTheAdministrator() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/ContactTheAdministrator");
        return modelAndView;
    }

    /**
     * 跳转图书创建
     *
     * @return
     */
    @GetMapping("/createBook")
    public ModelAndView toCreateBook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/CreateBook");
        return modelAndView;
    }


    /**
     * 跳转音频上传界面
     *
     * @param tableAudioManagement
     * @return
     */
    @GetMapping("/toCreateAudio")
    public ModelAndView toCreateAudio(TableAudioManagement tableAudioManagement) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/CreateAudio");
        modelAndView.addObject("tableAudioManagement", tableAudioManagement);
        return modelAndView;
    }

    /**
     * 书籍详细
     *
     * @param audioNameId
     * @return
     */
    @RequestMapping("/audioFull")
    public ModelAndView toAudio(String audioNameId) {
        ModelAndView modelAndView = new ModelAndView();
        TableAudioName tableAudioName = tableAudioNameService.getById(audioNameId);
        TableUser tableUser = tableUserService.getById(tableAudioName.getUId());
        TableAudioNameDTO tableAudioNameDTO = tableAudioNameService.tableAudioNameToTableAudioNameDTO(tableAudioName, tableUser.getUName());
        List<TableAudioManagement> tableAudioManagements = tableAudioManagementService.findMusicListByAudioNameId(audioNameId);
        //更新点击量
        boolean b = tableAudioNameService.updateClickThroughRateByAudioNameId(audioNameId, tableAudioName.getAudioNameCount() + 1);
        assert b;
        //查找月榜热度
        List<TableAudioNameDTO> monthTops = tableAudioNameService.monthTops();
        //周榜
        List<TableAudioNameDTO> weekTops = tableAudioNameService.weekTops();
        modelAndView.addObject("tableAudioNameDTO", tableAudioNameDTO);
        modelAndView.addObject("tableAudioManagements", tableAudioManagements);

        modelAndView.addObject("weekTops", weekTops);
        modelAndView.addObject("monthTops", monthTops);
        modelAndView.setViewName("view/Audio");
        return modelAndView;
    }
}
