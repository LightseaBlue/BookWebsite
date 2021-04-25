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
 * 总类型表   ps:   音乐    图书(TableAllTypes)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-22 12:41:09
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("总类型表   ps:   音乐    图书")
@TableName("table_all_types")
public class TableAllTypes extends Model<TableAllTypes> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键 ")
    @TableId(value = "ALL_TYPE_ID", type = IdType.AUTO)
    private Integer allTypeId;

    @ApiModelProperty("总类型名")
    @TableField(value = "ALL_TYPE_NAME")
    private String allTypeName;

    @ApiModelProperty("总类型图片")
    @TableField(value = "ALL_TYPE_IMG")
    private String allTypeImg;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "ALL_TYPE_FOUNDER")
    private String allTypeFounder;

    @ApiModelProperty("状态")
    @TableField(value = "ALL_TYPE_STU")
    private Integer allTypeStu;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
