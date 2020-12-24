package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 断点续听表(TableHistory)实体类
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
public class TableHistory extends Model<TableHistory> implements Serializable {
    private static final long serialVersionUID = -16876642613723850L;

    private Integer hId;

    @ApiModelProperty("用户id  做外键")
    private Integer uId;

    @ApiModelProperty("书籍id  做外键")
    private Integer audioNameId;

    @ApiModelProperty("音频id   做外键")
    private Integer audioId;

    @ApiModelProperty("该集听到的时间")
    private String hTime;

    private String reserved1;

    private String reserved2;

}