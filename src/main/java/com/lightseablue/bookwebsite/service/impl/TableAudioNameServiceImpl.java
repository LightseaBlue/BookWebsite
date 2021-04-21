package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableAudioNameDao;
import com.lightseablue.bookwebsite.dto.TableAudioNameDTO;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 书籍/专辑名名记录表   ps: 音乐:流行音乐:一个人(TableAudioName)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@Service("tableAudioNameService")
public class TableAudioNameServiceImpl extends ServiceImpl<TableAudioNameDao, TableAudioName> implements TableAudioNameService {
    @Autowired
    TableAudioNameDao dao;
    @Autowired
    TableUserService tableUserService;

    @Override
    public List<TableAudioNameDTO> getYouLike(Integer uid, int pageNum) {
        Page<TableAudioNameDTO> page = new Page<>();
        page.setSize(3);
        page.setCurrent(pageNum);
        return dao.findYouLike(page, uid).getRecords();
    }

    @Override
    public List<TableAudioNameDTO> findAllTopBook(int num) {
        Page<TableAudioNameDTO> page = new Page<>();
        page.setSize(3);
        page.setCurrent(num);
        return dao.findAllTopBook(page).getRecords();
    }

    @Override
    public List<TableAudioNameDTO> searchTopBookByType(int allTypeId) {
        return dao.searchTopBookByType(allTypeId);
    }

    @Override
    public List<TableAudioNameDTO> searchBooksByType(int allTypeId) {
        return dao.searchBooksByType(allTypeId);
    }

    @Override
    public Page<TableAudioName> searchAllBooksByType(int allTypeId, int thisPage) {
        QueryWrapper<TableAudioName> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TableAudioName::getAllTypeId, allTypeId);
        Page<TableAudioName> page = setBookListPage(thisPage);
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<TableAudioName> searchAllBooksByAudioTypeId(Integer audioTypeId, Integer thisPage) {
        QueryWrapper<TableAudioName> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TableAudioName::getAudioTypeId, audioTypeId);
        Page<TableAudioName> page = setBookListPage(thisPage);
        return this.page(page, queryWrapper);
    }

    private Page<TableAudioName> setBookListPage(Integer thisPage) {
        Page<TableAudioName> page = new Page<>();
        page.setCurrent(thisPage);
        page.setSize(6);
        return page;
    }

    @Override
    public List<TableAudioNameDTO> pageToList(IPage<TableAudioName> tableAudioNamePage) {
        List<TableAudioName> tableAudioNameS = tableAudioNamePage.getRecords();
        List<TableAudioNameDTO> tableAudioNameDtoS = new ArrayList<>();
        tableAudioNameS.forEach(e -> {
            TableUser tableUser = tableUserService.getById(e.getUId());
            TableAudioNameDTO tableAudioNameDTO = new TableAudioNameDTO();
            tableAudioNameDTO.setUName(tableUser.getUName());
            tableAudioNameDTO.setAudioNameName(e.getAudioNameName());
            tableAudioNameDTO.setAudioNameImg(e.getAudioNameImg());
            tableAudioNameDTO.setAllTypeId(e.getAllTypeId());
            tableAudioNameDTO.setAudioNameId(e.getAudioNameId());
            tableAudioNameDTO.setAudioNameCount(e.getAudioNameCount());
            tableAudioNameDtoS.add(tableAudioNameDTO);
        });
        return tableAudioNameDtoS;
    }
}