package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableUser;

/**
 * 用户表(TableUser表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_user数据传输对象", description = "请求/响应参数")
public class TableUserDTO extends TableUser {

    private static final long serialVersionUID = 6019054144879193181L;
}
