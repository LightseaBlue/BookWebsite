package com.lightseablue.bookwebsite.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 音频管理表      音频地址   (TableAudioManagement)实体类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "信息类")
public class TableAudioManagement extends Model<TableAudioManagement> implements Serializable {
    private static final long serialVersionUID = 803641539006867676L;

    private Integer audioId;

    @ApiModelProperty("做外键   书籍/专辑id")
    private String audioNameId;

    @ApiModelProperty("用户id  做外键")
    private Integer uId;

    @ApiModelProperty("音频名")
    private String audioName;

    @ApiModelProperty("上传时间")
    private Date audioUpdateTime;

    @ApiModelProperty("音频地址")
    private String audioAddress;

    private String reserved1;

    private String reserved2;

}