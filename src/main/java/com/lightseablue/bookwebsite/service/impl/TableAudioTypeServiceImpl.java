package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioTypeDao;
import com.lightseablue.bookwebsite.entity.TableAudioType;
import com.lightseablue.bookwebsite.service.TableAudioTypeService;
import org.springframework.stereotype.Service;

/**
 * 大类型下的小类型   ps:    音乐:流行音乐(TableAudioType)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:10
 */
@Service("tableAudioTypeService")
public class TableAudioTypeServiceImpl extends ServiceImpl<TableAudioTypeDao, TableAudioType> implements TableAudioTypeService {

}