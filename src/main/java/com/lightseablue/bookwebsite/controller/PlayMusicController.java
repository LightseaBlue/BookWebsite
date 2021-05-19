package com.lightseablue.bookwebsite.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lightseablue.bookwebsite.dto.PlayMusicDTO;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.entity.TableRecord;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableHistoryService;
import com.lightseablue.bookwebsite.service.TableRecordService;
import com.lightseablue.bookwebsite.service.TableUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: PlayMusicController
 * @Package: com.lightseablue.bookwebsite.controller
 * @Description: 控制音乐播放
 * @author: LightseaBlue
 * @date: 2021/4/19     15:16
 */
@RestController
@Api("播放控制类")
@RequestMapping("/playMusic")
public class PlayMusicController {
    @Autowired
    TableAudioManagementService tableAudioManagementService;

    @Autowired
    TableHistoryService tableHistoryService;

    @Autowired
    TableRecordService tableRecordService;
    @Autowired
    TableUserService tableUserService;

    @ApiOperation("添加音频列表")
    @GetMapping("/findMusicList")
    public PlayMusicDTO findMusicList(String audioNameId, String thisPage) throws Exception {
        int nextPage = Integer.parseInt(thisPage) + 1;
        List<TableAudioManagement> list = tableAudioManagementService
                .findMusicList(audioNameId, nextPage).getRecords();
        if (list == null) {
            return null;
        }
        return PlayMusicDTO.builder()
                .thisPage(nextPage)
                .list(list).build();
    }

    /**
     * 查找歌曲信息
     *
     * @param audioNameId
     * @param audioId
     * @return
     */
    @ApiOperation("查找歌曲信息")
    @GetMapping("/switchMusic")
    public PlayMusicDTO switchMusic(String audioNameId, String audioId) {
        PlayMusicDTO bean = tableAudioManagementService.getSrcById(Integer.parseInt(audioId));
        setAudioId(bean, audioNameId);
        return bean;
    }

    /**
     * 播放歌曲
     *
     * @param request
     * @param audioNameId
     */
    @GetMapping("/play")
    public PlayMusicDTO play(HttpServletRequest request, String audioNameId, Integer audioTypeId) {
        TableUser user = (TableUser) request.getSession().getAttribute("user");
        PlayMusicDTO bean;
        if (user != null) {
            //加入用户行为表
            TableRecord recordUser = tableRecordService.getUser(user.getUId(), audioTypeId);
            if (recordUser != null) {
                recordUser.setRNum(recordUser.getRNum() + 1);
            } else {
                recordUser = TableRecord.builder()
                        .uId(user.getUId())
                        .audioTypeId(audioTypeId)
                        .rNum(1L)
                        .build();
            }
            tableRecordService.saveOrUpdate(recordUser);
            bean = tableHistoryService.findUserFirstSrc(audioNameId, user.getUId());
            if (bean == null) {
                // todo: 断点续听没有数据
                bean = tableAudioManagementService.findFirstSrc(audioNameId);
            }
        } else {
            bean = tableAudioManagementService.findFirstSrc(audioNameId);
        }
        setAudioId(bean, audioNameId);
        //设置音乐列表当前页数,设置当前音乐列表
        //获取当前list的页数
        //TODO :这里没有获取断点续听的列表
        IPage<TableAudioManagement> musicList = tableAudioManagementService.findMusicList(audioNameId, 1);
        bean.setList(musicList.getRecords());
        bean.setThisPage(1);
        return bean;
    }

    /**
     * 设置上一页，下一页
     *
     * @param bean
     * @param audioNameId
     */
    private void setAudioId(PlayMusicDTO bean, String audioNameId) {
        Integer nextAudioId = bean.getAudioId() + 1;
        Integer preAudioId = bean.getAudioId() - 1;
        TableAudioManagement nextBookBean = tableAudioManagementService.getAudioName(audioNameId, nextAudioId);
        if (nextBookBean == null) {
            nextAudioId = bean.getAudioId();
        }
        TableAudioManagement preBookBean = tableAudioManagementService.getAudioName(audioNameId, preAudioId);
        if (preBookBean == null) {
            preAudioId = bean.getAudioId();
        }
        bean.setNextAudioId(nextAudioId);
        bean.setPreAudioId(preAudioId);
    }
}
