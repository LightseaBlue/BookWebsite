package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableRecord;

/**
 * 记录用户行为(TableRecord表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_record数据传输对象", description = "请求/响应参数")
public class TableRecordDTO extends TableRecord {

    private static final long serialVersionUID = -8277583147120641669L;
}
