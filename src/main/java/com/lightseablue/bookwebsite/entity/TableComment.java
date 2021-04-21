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
 * 评论表(TableComment)表实体类
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
@ApiModel("评论表")
@TableName("table_comment")
public class TableComment extends Model<TableComment> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "C_ID", type = IdType.AUTO)
    private Integer cId;

    @ApiModelProperty("用户id")
    @TableField(value = "U_ID")
    private Integer uId;

    @ApiModelProperty("书籍id")
    @TableField(value = "AUDIO_NAME_ID")
    private Integer audioNameId;

    @ApiModelProperty("评论的id")
    @TableField(value = "C_TITLE_ID")
    private Long cTitleId;

    @ApiModelProperty("评论的内容")
    @TableField(value = "C_TITLE")
    private String cTitle;

    @ApiModelProperty("回复评论的id    时间戳")
    @TableField(value = "C_CONTENTS_ID")
    private Long cContentsId;

    @ApiModelProperty("回复评论的内容")
    @TableField(value = "C_CONTENTS")
    private String cContents;

    @ApiModelProperty("评论时间")
    @TableField(value = "C_DATE")
    private Date cDate;

    @TableField(value = "RESERVED1")
    private String reserved1;

    @TableField(value = "RESERVED2")
    private String reserved2;
}
