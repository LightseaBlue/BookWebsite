package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableHistory;

/**
 * 断点续听表(TableHistory表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_history数据传输对象", description = "请求/响应参数")
public class TableHistoryDTO extends TableHistory {

    private static final long serialVersionUID = 8263445565675701350L;
}
