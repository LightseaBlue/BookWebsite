package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioManagementDao;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import org.springframework.stereotype.Service;

/**
 * 音频管理表      音频地址   (TableAudioManagement)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:05
 */
@Service("tableAudioManagementService")
public class TableAudioManagementServiceImpl extends ServiceImpl<TableAudioManagementDao, TableAudioManagement> implements TableAudioManagementService {

}