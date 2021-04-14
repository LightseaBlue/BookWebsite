package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)实体类
 *
 * @author LightseaBlue
 * @since 2021-01-13 18:20:54
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableAudioName extends Model<TableAudioName> implements Serializable {
    private static final long serialVersionUID = -52107156104516761L;

    private String audioNameId;

    @ApiModelProperty("大类型id   做外部关联")
    private Integer allTypeId;

    @ApiModelProperty("音频类型  做外部关联")
    private Integer audioTypeId;

    @ApiModelProperty("书籍/专辑名称")
    private String audioNameName;

    @ApiModelProperty("书籍/专辑简介")
    private String audioNameSummary;

    @ApiModelProperty("书籍图片")
    private String audioNameImg;

    @ApiModelProperty("书籍/专辑的作者")
    private String audioNameWriter;

    @ApiModelProperty("书籍/专辑状态    1  正常   2  下架   9 维护   默认 1")
    private String audioNameStatus;

    @ApiModelProperty("书籍/专辑的点击率")
    private Long audioNameCount;

    @ApiModelProperty("上传人 ")
    private Integer uId;

    private String reserved1;

    private String reserved2;

}