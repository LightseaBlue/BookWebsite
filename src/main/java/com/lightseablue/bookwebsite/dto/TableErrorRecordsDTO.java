package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;

/**
 * 爬取出错(TableErrorRecords表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_error_records数据传输对象", description = "请求/响应参数")
public class TableErrorRecordsDTO extends TableErrorRecords {

    private static final long serialVersionUID = 7869820837746438699L;
}
