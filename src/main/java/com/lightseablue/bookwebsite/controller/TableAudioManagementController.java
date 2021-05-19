package com.lightseablue.bookwebsite.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * 音频管理表      音频地址   (TableAudioManagement)表控制层
 *
 * @author LightseaBlue
 * @since 2020-12-24 17:26:27
 */
@RestController
@RequestMapping("/tableAudioManagement")
public class TableAudioManagementController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableAudioManagementService tableAudioManagementService;

    /**
     * 上传音频
     *
     * @param tableAudioManagement
     * @param request
     * @return
     */
    @PostMapping("/upDateAudio")
    @ResponseBody
    public String upDateAudio(TableAudioManagement tableAudioManagement, HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String parent = "Audio/" + tableAudioManagement.getAudioNameId();
        File f = new File(parent);
        if (!f.exists()) {
            return "false";
        }
        //这里走的没有音频名,多文件上传
        if ("".equals(tableAudioManagement.getAudioName())) {
            for (MultipartFile file : files) {
                long currentTimeMillis = System.currentTimeMillis();
                String child = currentTimeMillis + ".mp3";
                File dest = new File(parent, child);
                String absolutePath = dest.getAbsolutePath();
                try {
                    //保存文件
                    file.transferTo(Paths.get(absolutePath));
                    //入数据库
                    String fileName = file.getOriginalFilename();
                    assert fileName != null;
                    String substring = fileName.substring(0, fileName.indexOf("."));
                    tableAudioManagement.setAudioName(substring);
                    tableAudioManagement.setAudioUpdateTime(new Date());
                    tableAudioManagement.setAudioAddress(parent + "/" + child);
                    tableAudioManagementService.save(tableAudioManagement);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    return "false";
                }
            }
            return tableAudioManagement.getAudioNameId();
        } else {
            String child = System.currentTimeMillis() + ".mp3";
            File dest = new File(parent, child);
            String absolutePath = dest.getAbsolutePath();
            try {
                //保存文件
                files.get(0).transferTo(Paths.get(absolutePath));
                //入数据库
                tableAudioManagement.setAudioUpdateTime(new Date());
                tableAudioManagement.setAudioAddress(parent + "/" + child);
                tableAudioManagementService.save(tableAudioManagement);
                return tableAudioManagement.getAudioNameId();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return "false";
            }
        }
    }
}