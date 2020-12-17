package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes)实体类
 *
 * @author LightseaBlue
 * @since 2020-12-16 22:29:55
 */

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "信息类")
public class TableAllTypes extends Model<TableAllTypes> implements Serializable {
    private static final long serialVersionUID = -98167832398885922L;

    @ApiModelProperty("主键")
    private Integer allTypeId;

    @ApiModelProperty("总类型名")
    private String allTypeName;

    @ApiModelProperty("总类型图片")
    private String allTypeImg;

    @ApiModelProperty("创建人姓名")
    private String allTypeFounder;

    private String reserved1;

    private String reserved2;
}