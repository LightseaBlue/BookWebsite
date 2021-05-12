package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableAdmin;

/**
 * 管理员(TableAdmin表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-05-08 11:58:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_admin数据传输对象", description = "请求/响应参数")
public class TableAdminDTO extends TableAdmin {

}
