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
 * 记录用户行为(TableRecord)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-18 14:51:53
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("记录用户行为")
@TableName("table_record")
public class TableRecord extends Model<TableRecord> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "R_ID", type = IdType.AUTO)
    private Integer rId;

    @ApiModelProperty("用户id  做外键")
    @TableField(value = "U_ID")
    private Integer uId;

    @ApiModelProperty("书籍的id")
    @TableField(value = "AUDIO_NAME_ID")
    private Integer audioNameId;

    @ApiModelProperty("音频类型id  做外键")
    @TableField(value = "AUDIO_TYPE_ID")
    private Integer audioTypeId;

    @ApiModelProperty("所有类型id  做外键")
    @TableField(value = "ALL_TYPE_ID")
    private Integer allTypeId;

    @ApiModelProperty("记录点击次数")
    @TableField(value = "R_NUM")
    private Long rNum;

    @TableField(value = "RESERVED1")
    private String reserved1;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
