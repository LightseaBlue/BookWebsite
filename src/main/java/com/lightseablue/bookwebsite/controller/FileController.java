package com.lightseablue.bookwebsite.controller;

import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: FileController
 * @Package: com.lightseablue.bookwebsite.controller
 * @Description:文件处理 上传或者下载
 * @author: LightseaBlue
 * @date: 2021/4/22     22:27
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Resource
    TableAudioNameService tableAudioNameService;
    @Resource
    TableAudioManagementService tableAudioManagementService;

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String audioAddress, HttpServletRequest req) throws IOException {
        // 获取文件存放的真实路径
        String fileName = new Date().toString() + ".mp3";
        //创建文件实例
        File file = new File(audioAddress);
        //修改文件名的编码格式
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
        //设置httpHeaders,使浏览器响应下载
        HttpHeaders headers = new HttpHeaders();
        //告诉浏览器执行下载的操作，“attachment”告诉了浏览器进行下载,下载的文件 文件名为 downloadFileName
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //设置响应方式为二进制，以二进制流传输
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @GetMapping("/delAudio")
    @ResponseBody
    public boolean delAudio(Integer audioId) {
        return tableAudioManagementService.updateStu(audioId);
    }

    @GetMapping("delAudioName")
    @ResponseBody
    public boolean delAudioName(String audioNameId) {
        return tableAudioNameService.updateByAudioNameId(audioNameId);
    }
}
