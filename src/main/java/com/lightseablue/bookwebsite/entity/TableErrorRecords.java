package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 爬取出错(TableErrorRecords)实体类
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
public class TableErrorRecords extends Model<TableErrorRecords> implements Serializable {
    private static final long serialVersionUID = 922985148799624939L;

    @ApiModelProperty("爬取错误id")
    private Integer eId;

    @ApiModelProperty("爬取出现错误的用户")
    private String eUser;

    @ApiModelProperty("爬取出现错误的时间")
    private Date eTime;

    @ApiModelProperty("问题解决  1解决   2无法解决   3 暂挂")
    private Integer eStatus;

    private String eName;

}