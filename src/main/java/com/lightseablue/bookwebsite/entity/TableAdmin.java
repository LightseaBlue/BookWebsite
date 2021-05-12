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
 * 管理员(TableAdmin)表实体类
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:33
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("管理员")
@TableName("table_admin")
public class TableAdmin extends Model<TableAdmin> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    @ApiModelProperty("管理员名")
    @TableField(value = "a_name")
    private String aName;

    @ApiModelProperty("管理员密码")
    @TableField(value = "a_pwd")
    private String aPwd;

    @ApiModelProperty("管理员状态 职位")
    @TableField(value = "a_stu")
    private Integer aStu;

    @ApiModelProperty("头像")
    @TableField(value = "a_img")
    private String aImg;
}
