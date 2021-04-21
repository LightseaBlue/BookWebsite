package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableAllTypes;

/**
 * 总类型表   ps:   音乐    图书(TableAllTypes表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_all_types数据传输对象", description = "请求/响应参数")
public class TableAllTypesDTO extends TableAllTypes {

    private static final long serialVersionUID = 5404050623741352019L;
}
