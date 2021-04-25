package com.lightseablue.bookwebsite.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表服务接口
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
public interface TableAudioNameService extends IService<TableAudioName> {

    /**
     * 根据id删除 大的书籍   变状态
     *
     * @param audioNameId
     * @return
     */
    boolean updateByAudioNameId(String audioNameId);

    /**
     * 月榜前五
     *
     * @return
     */
    List<TableAudioNameDTO> monthTops();

    /**
     * 周榜前五
     *
     * @return
     */
    List<TableAudioNameDTO> weekTops();

    /**
     * 查询你的喜欢
     *
     * @param uid
     * @param num
     * @return
     */
    List<TableAudioNameDTO> getYouLike(Integer uid, int num);

    /**
     * 查询排行榜
     *
     * @param num
     * @return
     */
    List<TableAudioNameDTO> findAllTopBook(int num);

    /**
     * 按大类型查找每一个类型的排行榜
     *
     * @return
     */
    List<TableAudioNameDTO> searchTopBookByType(int allTypeId);


    /**
     * 根据大类型查找一个类型的5本书
     *
     * @param allTypeId
     * @return
     */
    List<TableAudioNameDTO> searchBooksByType(int allTypeId);

    /**
     * 根据大类型查找一个类型的全部
     *
     * @param allTypeId
     * @return
     */
    Page<TableAudioName> searchAllBooksByType(int allTypeId, int thisPage);

    /**
     * 根据小类型查看一个小类型的全部
     *
     * @param audioNameId
     * @param thisPage
     * @return
     */
    Page<TableAudioName> searchAllBooksByAudioTypeId(Integer audioNameId, Integer thisPage);

    /**
     * 搜索书籍
     *
     * @param bookName
     * @param thisPage
     * @param uid
     * @return
     */
    Page<TableAudioName> searchLikeBooks(String bookName, Integer thisPage, List<Integer> uid);

    /**
     * 根据播讲人查找
     *
     * @param uid
     * @param thisPage
     * @return
     */
    Page<TableAudioName> searchBooksByUid(Integer uid, Integer thisPage);

    /**
     * page转换list
     *
     * @return
     */
    List<TableAudioNameDTO> pageToList(IPage<TableAudioName> tableAudioNameIPage);

    /**
     * 转dto
     *
     * @param tableAudioName
     * @param uName
     * @return
     */
    TableAudioNameDTO tableAudioNameToTableAudioNameDTO(TableAudioName tableAudioName, String uName);
}