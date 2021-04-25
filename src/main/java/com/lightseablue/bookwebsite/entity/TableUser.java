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
 * 用户表(TableUser)表实体类
 *
 * @author LightseaBlue
 * @since 2021-04-22 12:43:47
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户表")
@TableName("table_user")
public class TableUser extends Model<TableUser> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "U_ID", type = IdType.AUTO)
    private Integer uId;

    @ApiModelProperty("用户名")
    @TableField(value = "U_NAME")
    private String uName;

    @ApiModelProperty("邮箱")
    @TableField(value = "U_EMAIL")
    private String uEmail;

    @ApiModelProperty("性别")
    @TableField(value = "U_SEX")
    private String uSex;

    @ApiModelProperty("密码")
    @TableField(value = "U_PWD")
    private String uPwd;

    @ApiModelProperty("头像路径")
    @TableField(value = "U_PHOTO")
    private String uPhoto;

    @ApiModelProperty("关于自己介绍")
    @TableField(value = "U_ABOUT")
    private String uAbout;

    @ApiModelProperty("自己擅长的说书类型")
    @TableField(value = "U_GOOD_AT")
    private String uGoodAt;

    @TableField(value = "U_STU")
    private Integer uStu;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
