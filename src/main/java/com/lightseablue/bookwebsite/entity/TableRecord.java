package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 记录用户行为(TableRecord)实体类
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
public class TableRecord extends Model<TableRecord> implements Serializable {
    private static final long serialVersionUID = -75087477677680969L;

    private Integer rId;

    @ApiModelProperty("用户id  做外键")
    private Integer uId;

    @ApiModelProperty("音频类型id  做外键")
    private Integer audioTypeId;

    @ApiModelProperty("所有类型id  做外键")
    private Integer allTypeId;

    @ApiModelProperty("记录点击次数")
    private Long rNum;

    private String reserved1;

    private String reserved2;

}