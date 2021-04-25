package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PlayMusicDTO
 * @Package: com.lightseablue.bookwebsite.dto
 * @Description: 音乐传输层
 * @author: LightseaBlue
 * @date: 2021/4/19     16:36
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("音乐传输层")
public class PlayMusicDTO implements Serializable {
    private static final long serialVersionUID = 5906914212634425006L;
    @ApiModelProperty("章节时间")
    private Date audioTime;
    @ApiModelProperty("章节名称")
    private String audioName;
    @ApiModelProperty("书籍图片")
    private String audioNameImg;
    @ApiModelProperty("数据类型id")
    private Integer audioTypeId;
    @ApiModelProperty("章节音频地址")
    private String audioAddress;
    @ApiModelProperty("当前章节id")
    private Integer audioId;
    @ApiModelProperty("书籍id")
    private String audioNameId;
    @ApiModelProperty("上一章节id")
    private Integer preAudioId;
    @ApiModelProperty("下一章节id")
    private Integer nextAudioId;
    @ApiModelProperty("列表（歌单）")
    private List<TableAudioManagement> list;
    @ApiModelProperty("当前歌单页")
    private Integer thisPage;
}
