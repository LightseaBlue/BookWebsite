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
 * 音频管理表      音频地址   (TableAudioManagement)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:51:52
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("音频管理表      音频地址   ")
@TableName("table_audio_management")
public class TableAudioManagement extends Model<TableAudioManagement> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "AUDIO_ID", type = IdType.AUTO)
    private Integer audioId;

    @ApiModelProperty("做外键   书籍/专辑id")
    @TableField(value = "AUDIO_NAME_ID")
    private String audioNameId;

    @ApiModelProperty("用户id  做外键")
    @TableField(value = "U_ID")
    private Integer uId;

    @ApiModelProperty("音频名")
    @TableField(value = "AUDIO_NAME")
    private String audioName;

    @ApiModelProperty("上传时间")
    @TableField(value = "AUDIO_UPDATE_TIME")
    private Date audioUpdateTime;

    @ApiModelProperty("音频地址")
    @TableField(value = "AUDIO_ADDRESS")
    private String audioAddress;

    @TableField(value = "RESERVED1")
    private String reserved1;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
