package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 用户表(TableUser)实体类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:29
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "信息类")
public class TableUser extends Model<TableUser> implements Serializable {
    private static final long serialVersionUID = -72535852788877269L;

    @ApiModelProperty("用户id ")
    private Integer uId;

    @ApiModelProperty("用户名 ")
    private String uName;

    @ApiModelProperty("邮箱 ")
    private String uEmail;

    @ApiModelProperty("性别 ")
    private String uSex;

    @ApiModelProperty("密码")
    private String uPwd;

    @ApiModelProperty("头像路径")
    private String uPhoto;

    @ApiModelProperty("关于自己介绍")
    private String uAbout;

    @ApiModelProperty("自己擅长的说书类型")
    private String uGoodAt;

    private String reserved1;

    private String reserved2;
}