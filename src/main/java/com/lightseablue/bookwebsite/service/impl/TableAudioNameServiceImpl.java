package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioNameDao;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import org.springframework.stereotype.Service;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Service("tableAudioNameService")
public class TableAudioNameServiceImpl extends ServiceImpl<TableAudioNameDao, TableAudioName> implements TableAudioNameService {

}