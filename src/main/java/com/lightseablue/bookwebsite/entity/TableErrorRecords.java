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
 * 爬取出错(TableErrorRecords)表实体类
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
@ApiModel("爬取出错")
@TableName("table_error_records")
public class TableErrorRecords extends Model<TableErrorRecords> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("爬取错误id")
    @TableId(value = "e_id", type = IdType.AUTO)
    private Integer eId;

    @ApiModelProperty("爬取出现错误的用户")
    @TableField(value = "e_user")
    private String eUser;

    @ApiModelProperty("爬取出现错误的时间")
    @TableField(value = "e_time")
    private Date eTime;

    @ApiModelProperty("问题解决  1解决   2无法解决   3 暂挂")
    @TableField(value = "e_status")
    private Integer eStatus;

    @TableField(value = "e_name")
    private String eName;
}
