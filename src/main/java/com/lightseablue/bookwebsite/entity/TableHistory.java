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
 * 断点续听表(TableHistory)表实体类
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
@ApiModel("断点续听表")
@TableName("table_history")
public class TableHistory extends Model<TableHistory> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "H_ID", type = IdType.AUTO)
    private Integer hId;

    @ApiModelProperty("用户id  做外键")
    @TableField(value = "U_ID")
    private Integer uId;

    @ApiModelProperty("书籍id  做外键")
    @TableField(value = "AUDIO_NAME_ID")
    private Integer audioNameId;

    @ApiModelProperty("音频id   做外键")
    @TableField(value = "AUDIO_ID")
    private Integer audioId;

    @ApiModelProperty("该集听到的时间")
    @TableField(value = "H_TIME")
    private String hTime;

    @TableField(value = "RESERVED1")
    private String reserved1;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
