package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 断点续听表(TableHistory)实体类
 *
 * @author LightseaBlue
 * @since 2021-01-13 18:20:56
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "断点续听表")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableHistory extends Model<TableHistory> implements Serializable {
    private static final long serialVersionUID = 817972859152472338L;

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