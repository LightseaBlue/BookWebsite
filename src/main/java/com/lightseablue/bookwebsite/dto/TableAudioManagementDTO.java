package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;

/**
 * 音频管理表      音频地址   (TableAudioManagement表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_audio_management数据传输对象", description = "请求/响应参数")
public class TableAudioManagementDTO extends TableAudioManagement {

    private static final long serialVersionUID = -2937455155951090408L;
}
