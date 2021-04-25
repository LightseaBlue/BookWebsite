package com.lightseablue.bookwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表数据库访问层
 *
 * @author LightseaBlue
 * @since 2021-04-17 22:41:37
 */
@Repository
public interface TableAudioNameDao extends BaseMapper<TableAudioName> {
    @ApiOperation("查询你的喜欢")
    @Select("select AUDIO_TYPE_ID,AUDIO_NAME_ID,audio_name_name,AUDIO_NAME_IMG,\n" +
            "(SELECT U_NAME FROM table_user WHERE U_ID=b.u_id) uname from table_audio_name b\n" +
            "where exists(select u.AUDIO_TYPE_ID from table_record u where\n" +
            "u.U_ID=#{uid} and b.AUDIO_TYPE_ID=u.AUDIO_TYPE_ID order by R_NUM desc) and b.AUDIO_NAME_STATUS=1\n" +
            "ORDER BY AUDIO_NAME_COUNT desc")
    IPage<TableAudioNameDTO> findYouLike(Page<TableAudioNameDTO> page, Integer uid);

    @ApiOperation("查询所有图书信息按排行")
    @Select("select AUDIO_TYPE_ID,AUDIO_NAME_ID,audio_name_name,AUDIO_NAME_IMG,\n" +
            "(SELECT U_NAME FROM table_user WHERE U_ID=b.u_id) uname FROM\n" +
            "table_audio_name b where b.AUDIO_NAME_STATUS=1 ORDER BY AUDIO_NAME_COUNT desc")
    IPage<TableAudioNameDTO> findAllTopBook(Page<TableAudioNameDTO> page);

    @ApiOperation("按大类型查看每一个大类型的排行")
    @Select("select AUDIO_TYPE_ID,AUDIO_NAME_ID,audio_name_name,AUDIO_NAME_IMG,\n" +
            "(SELECT U_NAME FROM table_user WHERE U_ID=b.u_id) uname from table_audio_name b\n" +
            "where ALL_TYPE_ID=#{allTypeId} and b.AUDIO_NAME_STATUS=1 ORDER BY AUDIO_NAME_COUNT desc LIMIT 0,5")
    List<TableAudioNameDTO> searchTopBookByType(int allTypeId);

    @ApiOperation("按大类型查看最新的5本书的信息")
    @Select("select AUDIO_TYPE_ID,AUDIO_NAME_ID,audio_name_name,AUDIO_NAME_IMG,(select U_NAME from table_user\n" +
            "where U_ID=b.U_ID) uname from table_audio_name b\n" +
            "where ALL_TYPE_ID=#{allTypeId} and b.AUDIO_NAME_STATUS=1 ORDER BY AUDIO_NAME_ID deSC  LIMIT 5;")
    List<TableAudioNameDTO> searchBooksByType(int allTypeId);


}
