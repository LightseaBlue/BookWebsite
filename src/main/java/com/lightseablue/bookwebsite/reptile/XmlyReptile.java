package com.lightseablue.bookwebsite.reptile;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableErrorRecordsService;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;

/**
 * @Program: bookwebsite
 * @ClassName: one
 * @Author: LightseaBlue
 * @Date: 2020-12-23 14:52
 * @Version: V1.0
 */
@Component
public class XmlyReptile {

    @Autowired
    private TableErrorRecordsService errorRecordsService;

    @Autowired
    private TableAudioNameService tableAudioNameService;

    @Autowired
    private TableAudioManagementService tableAudioManagementService;

    @Transactional(rollbackFor = Exception.class)
    public void reptileAudio(String name) throws IOException {
        //创建目录
        File directory = new File("src/main/resources/static");
        String courseFile = directory.getCanonicalPath();
        String path = courseFile + "/Audio/" + name + "/";
        File f = new File(path);
        if (!f.exists()) {
            FileUtils.forceMkdir(f);
        }
        //书籍id使用时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        String bookId = getAudioMes(name, path, timestamp);

        getAudio(bookId, name, path, timestamp);

    }

    private String getSummary(String bookId) throws IOException {
        Document search = Jsoup
                .connect("https://www.ximalaya.com/youshengshu/" + bookId + "/")
                .userAgent("Mozilla")
                .ignoreContentType(true)
                .timeout(5000)
                .get();

        return search.select("p[data-flag]").text();
    }

    /**
     * 获取书籍音频地址   存音频   存音频信息到数据库
     *
     * @param bookId
     * @param name
     * @param path
     * @throws IOException
     */
    private void getAudio(String bookId, String name, String path, String timestamp) throws IOException {
        // TODO: 2020/12/23 这里只爬了3页    直接死循环即可
        for (int num = 1; num < 3; num++) {
            Document tracksList = Jsoup
                    .connect("https://www.ximalaya.com/revision/album/v1/getTracksList?albumId=" +
                            bookId + "&pageNum=" + num)
                    .userAgent("Mozilla")
                    .ignoreContentType(true)
                    .timeout(5000)
                    .get();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tracksListNode = objectMapper.readTree(tracksList.body().text());
            JsonNode tracksListData = tracksListNode.get("data").get("tracks");
            //如果不存在则到了最后一页
            if (tracksListData == null) {
                break;
            }

            if (tracksListData.isArray()) {
                for (JsonNode objNode : tracksListData) {
                    //获取音频地址的id
                    String audioId = objNode.get("url").asText().split("/")[3];
                    String title = objNode.get("title").asText();
                    String index = objNode.get("index").asText();

                    //获取音频地址
                    Document audio = Jsoup
                            .connect("https://www.ximalaya.com/revision/play/v1/audio?id=" + audioId + "&ptype=1")
                            .userAgent("Mozilla")
                            .ignoreContentType(true)
                            .timeout(10000)
                            .get();
                    JsonNode jsonNode = objectMapper.readTree(audio.body().text());
                    String audioSrc = jsonNode.get("data").get("src").asText();

                    // TODO: 2020/12/23 修改user
                    if (audioSrc == null) {
                        TableErrorRecords errorRecords = TableErrorRecords.builder()
                                .eTime(new Date())
                                .eUser(null)
                                .eName(name)
                                .build();
                        errorRecordsService.save(errorRecords);
                    }

                    //下载音频
                    String fileName = index + "." + title + ".mp3";
                    saveAudioOrImg(audioSrc, path, fileName);
                    // TODO: 2020/12/24 记得改uid
                    TableAudioManagement tableAudioManagement = TableAudioManagement.builder()
                            .audioName(title)
                            .audioNameId(timestamp)
                            .uId(1)
                            .audioUpdateTime(new Date())
                            .audioAddress("/static/Audio/" + name + "/" + fileName)
                            .build();
                    saveAudioToDatabase(tableAudioManagement);
                }
            }
        }
    }

    /**
     * 把音频数据存入数据库
     *
     * @param tableAudioManagement
     */
    private void saveAudioToDatabase(TableAudioManagement tableAudioManagement) {
        System.out.println(tableAudioManagement);
        tableAudioManagementService.save(tableAudioManagement);
    }

    /**
     * 获取书籍id  书籍照片  存书籍信息到数据库
     *
     * @param name
     * @param path
     * @return
     * @throws IOException
     */
    private String getAudioMes(String name, String path, String timestamp) throws IOException {
        Document search = Jsoup
                .connect("https://www.ximalaya.com/search/" + name + "/")
                .userAgent("Mozilla")
                .ignoreContentType(true)
                .timeout(5000)
                .get();
        //爬取搜索的第一个图书的id
        Element searchElement = search.getElementsByClass("xm-album-cover__wrapper").first();
        if (searchElement == null) {
            return null;
        }
        String[] split = searchElement.getElementsByTag("a").attr("href").split("/");
        //获取书籍id
        String bookId = split[2];
        //获取音频类型  有声书或者音乐
        String bookType = split[1];
        //默认为有声书
        int allTypeId = 2;
        if ("yinyue".equals(bookType)) {
            allTypeId = 1;
        } else if ("youshengshu".equals(bookType)) {
            allTypeId = 2;
        } else if ("jiaoyupeixun".equals(bookType)) {
            allTypeId = 3;
        } else if ("ertong".equals(bookType)) {
            allTypeId = 4;
        } else if ("keji".equals(bookType)) {
            allTypeId = 5;
        }
        //获取书籍图片的路劲
        String imgSrc = searchElement.getElementsByTag("img").attr("abs:src");
        //获取书籍名
        String audioName = search.getElementsByClass("xm-album-title ellipsis-2").first().attr("title");
        //获取简介
        String summary = getSummary(bookId);

        String fileName = System.currentTimeMillis() + ".jpg";
        // TODO: 2020/12/24 记得改uid
        TableAudioName tableAudioName = TableAudioName.builder()
                .allTypeId(allTypeId)
                .audioNameName(audioName)
                .audioNameSummary(summary)
                .uId(1)
                .audioNameWriter("")
                .audioNameId(timestamp)
                .audioNameImg("/static/Audio/" + name + "/" + fileName)
                .build();
        //存照片
        saveAudioOrImg(imgSrc, path, fileName);
        //把照片存入数据库
        saveImgToDatabase(tableAudioName);

        return bookId;
    }

    /**
     * 通过连接获取书籍图片及音频
     *
     * @throws IOException
     */
    private void saveAudioOrImg(String src, String path, String fileName) throws IOException {
        //下载音乐
        Connection.Response response = Jsoup
                .connect(src)
                .userAgent("Mozilla")
                .ignoreContentType(true)
                .timeout(3 * 1000)
                .execute();
        BufferedInputStream bufferedInputStream = response.bodyStream();
        System.out.println(response.contentType());
        //保存文件的地址
        saveFile(bufferedInputStream, path, fileName);
    }

    /**
     * 把图片书籍信息存入数据库
     *
     * @param tableAudioName
     */
    private void saveImgToDatabase(TableAudioName tableAudioName) {
        System.out.println(tableAudioName);
        tableAudioNameService.save(tableAudioName);
    }

    /**
     * io流储存文件
     *
     * @param in
     * @param savePath
     * @param fileName
     * @throws IOException
     */
    private void saveFile(BufferedInputStream in, String savePath, String fileName) throws IOException {
        File file = new File(savePath, fileName);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
        }
        out.flush();

        //关闭缓冲流
        in.close();
        out.close();
    }
}