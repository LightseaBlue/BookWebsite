package com.lightseablue.bookwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lightseablue.bookwebsite.dao.TableCommentDao;
import com.lightseablue.bookwebsite.entity.TableComment;
import com.lightseablue.bookwebsite.service.TableCommentService;
import org.springframework.stereotype.Service;

/**
 * 评论表(TableComment)表服务实现类
 *
 * @author LightseaBlue
 * @since 2020-12-16 19:28:13
 */
@Service("tableCommentService")
public class TableCommentServiceImpl extends ServiceImpl<TableCommentDao, TableComment> implements TableCommentService {

}