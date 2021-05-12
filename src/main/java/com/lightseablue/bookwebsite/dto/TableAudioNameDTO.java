package com.lightseablue.bookwebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lightseablue.bookwebsite.entity.TableAudioName;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName表)   数据传输
 *
 * @author LightseaBlue
 * @since 2021-04-18 15:08:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "table_audio_name数据传输对象", description = "请求/响应参数")
public class TableAudioNameDTO extends TableAudioName {
    private static final long serialVersionUID = -3034505020475491394L;
    private String uName;
    private String allTypeName;
    private String audioTypeName;

    //分页时使用
    private Long total;
}
