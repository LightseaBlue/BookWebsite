package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表(TableComment)实体类
 *
 * @author LightseaBlue
 * @since 2020-12-16 22:30:00
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "信息类")
public class TableComment extends Model<TableComment> implements Serializable {
    private static final long serialVersionUID = -76936628335800819L;

    private Integer cId;

    @ApiModelProperty("用户id")
    private Integer uId;

    @ApiModelProperty("书籍id")
    private Integer audioNameId;

    @ApiModelProperty("评论的id")
    private Long cTitleId;

    @ApiModelProperty("评论的内容")
    private String cTitle;

    @ApiModelProperty("回复评论的id    时间戳")
    private Long cContentsId;

    @ApiModelProperty("回复评论的内容")
    private String cContents;

    @ApiModelProperty("评论时间")
    private Date cDate;

    private String reserved1;

    private String reserved2;

}