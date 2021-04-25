package com.lightseablue.bookwebsite.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-22 17:47:52
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人")
@TableName("table_audio_name")
public class TableAudioName extends Model<TableAudioName> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "AUDIO_NAME_ID", type = IdType.INPUT)
    private String audioNameId;

    @ApiModelProperty("大类型id   做外部关联")
    @TableField(value = "ALL_TYPE_ID")
    private Integer allTypeId;

    @ApiModelProperty("音频类型  做外部关联")
    @TableField(value = "AUDIO_TYPE_ID")
    private Integer audioTypeId;

    @ApiModelProperty("书籍/专辑名称")
    @TableField(value = "AUDIO_NAME_NAME")
    private String audioNameName;

    @ApiModelProperty("书籍/专辑简介")
    @TableField(value = "AUDIO_NAME_SUMMARY")
    private String audioNameSummary;

    @ApiModelProperty("书籍图片")
    @TableField(value = "AUDIO_NAME_IMG")
    private String audioNameImg;

    @ApiModelProperty("书籍/专辑的作者")
    @TableField(value = "AUDIO_NAME_WRITER")
    private String audioNameWriter;

    @ApiModelProperty("书籍/专辑状态    1  正常   2  下架   9 维护   默认 1")
    @TableField(value = "AUDIO_NAME_STATUS")
    private String audioNameStatus;

    @ApiModelProperty("书籍/专辑的点击率")
    @TableField(value = "AUDIO_NAME_COUNT")
    private Long audioNameCount;

    @ApiModelProperty("上传人 ")
    @TableField(value = "U_ID")
    private Integer uId;

    @TableField(value = "AUDIO_NAME_DATE")
    private Date audioNameDate;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
