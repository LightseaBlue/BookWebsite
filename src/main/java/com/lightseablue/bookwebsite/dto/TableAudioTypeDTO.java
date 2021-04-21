package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableAudioType;

/**
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_audio_type数据传输对象", description = "请求/响应参数")
public class TableAudioTypeDTO extends TableAudioType {

    private static final long serialVersionUID = -1684559074885560895L;
}
