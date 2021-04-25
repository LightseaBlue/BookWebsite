package com.lightseablue.bookwebsite.entity;

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
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-22 12:43:14
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("大类型下的小类型   ps:    音乐:流行音乐")
@TableName("table_audio_type")
public class TableAudioType extends Model<TableAudioType> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "AUDIO_TYPE_ID", type = IdType.AUTO)
    private Integer audioTypeId;

    @ApiModelProperty("所有类型id   做关联使用")
    @TableField(value = "ALL_TYPE_ID")
    private Integer allTypeId;

    @ApiModelProperty("小类型名")
    @TableField(value = "AUDIO_TYPE_NAME")
    private String audioTypeName;

    @ApiModelProperty("创建人")
    @TableField(value = "AUDIO_TYPE_USER")
    private String audioTypeUser;

    @ApiModelProperty("图片")
    @TableField(value = "AUDIO_TYPE_IMG")
    private String audioTypeImg;

    @TableField(value = "AUDIO_TYPE_STU")
    private Integer audioTypeStu;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
