package com.lightseablue.bookwebsite.reptile.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightseablue.bookwebsite.entity.TableAudioManagement;
import com.lightseablue.bookwebsite.entity.TableAudioName;
import com.lightseablue.bookwebsite.entity.TableErrorRecords;
import com.lightseablue.bookwebsite.entity.TableUser;
import com.lightseablue.bookwebsite.reptile.Reptile;
import com.lightseablue.bookwebsite.service.TableAudioManagementService;
import com.lightseablue.bookwebsite.service.TableAudioNameService;
import com.lightseablue.bookwebsite.service.TableErrorRecordsService;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.Random;

/**
 * @Program: bookwebsite
 * @ClassName: one
 * @Author: LightseaBlue
 * @Date: 2020-12-23 14:52
 * @Version: V1.0
 */
@Component
public class XmlyReptile implements Reptile {

    @Resource
    private TableErrorRecordsService errorRecordsService;

    @Resource
    private TableAudioNameService tableAudioNameService;

    @Resource
    private TableAudioManagementService tableAudioManagementService;

    String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};

    String[] agentServices = {
            "175.42.128.150",
            "117.91.254.225",
            "175.42.122.4",
            "183.166.103.217",
            "175.42.158.180",
            "114.230.126.83",
            "175.42.68.242",
            "123.169.114.57",
            "110.243.20.224",
            "175.43.143.72",
            "223.242.225.140"};

    @Transactional(rollbackFor = IOException.class)
    public void reptileAudio(String name, int audioTypeId, TableUser tableUser) throws IOException {
        //创建目录
        File directory = new File("src/main/resources/static");
        String courseFile = directory.getCanonicalPath();
        String path = courseFile + "/Audio/" + name + "/";
        File f = new File(path);
        if (!f.exists()) {
            FileUtils.forceMkdir(f);
        }
        //设置代理
        System.getProperties().setProperty("http.proxyHost", getAgentService());
        System.getProperties().setProperty("http.proxyPort", "9999");
        //书籍id使用时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        String bookId = getAudioMes(name, path, timestamp, audioTypeId, tableUser);

        getAudio(bookId, name, path, timestamp, tableUser);

    }

    private String getSummary(String bookId) throws IOException {
        Document search = Jsoup
                .connect("https://www.ximalaya.com/youshengshu/" + bookId + "/")
                .userAgent(getUa())
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
    private void getAudio(String bookId, String name, String path, String timestamp, TableUser tableUser) throws IOException {
        //指针   如果有2次没有获取到src  则退出
        int pointer = 1;
        // TODO: 2020/12/23 这里只爬了3页    直接死循环即可
        for (int num = 1; num < 2; num++) {
            Document tracksList = Jsoup
                    .connect("https://www.ximalaya.com/revision/album/v1/getTracksList?albumId=" +
                            bookId + "&pageNum=" + num)
                    .userAgent(getUa())
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

                    //获取音频地址
                    Document audio = Jsoup
                            .connect("https://www.ximalaya.com/revision/play/v1/audio?id=" + audioId + "&ptype=1")
                            .userAgent(getUa())
                            .ignoreContentType(true)
                            .timeout(10000)
                            .get();
                    JsonNode jsonNode = objectMapper.readTree(audio.body().text());
                    String audioSrc = jsonNode.get("data").get("src").asText();

                    if ("null".equals(audioSrc.trim()) || "".equals(audioSrc.trim())) {
                        TableErrorRecords errorRecords = TableErrorRecords.builder()
                                .eTime(new Date())
                                .eUser(tableUser.getUName())
                                .eName(name)
                                .build();
                        errorRecordsService.save(errorRecords);
                        if (pointer == 2) {
                            return;
                        }
                        pointer++;
                        continue;
                    }

                    //下载音频
                    String fileName = "audio" + System.currentTimeMillis() + ".mp3";
                    saveAudioOrImg(audioSrc, path, fileName);
                    TableAudioManagement tableAudioManagement = TableAudioManagement.builder()
                            .audioName(title)
                            .audioNameId(timestamp)
                            .uId(tableUser.getUId())
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
    private String getAudioMes(String name, String path, String timestamp, int audioTypeId, TableUser tableUser) throws IOException {
        Document search = Jsoup
                .connect("https://www.ximalaya.com/search/" + name + "/")
                .userAgent(getUa())
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
        if ("yinyue".equals(bookType) || "xiangsheng".equals(bookType)) {
            allTypeId = 1;
        } else if ("youshengshu".equals(bookType)) {
            allTypeId = 2;
        } else if ("waiyu".equals(bookType) || "lishi".equals(bookType)
                || "renwen".equals(bookType)) {
            allTypeId = 3;
        } else if ("ertong".equals(bookType)) {
            allTypeId = 4;
        } else if ("keji".equals(bookType) || "shangye".equals(bookType) || "jiaoyupeixun".equals(bookType)) {
            allTypeId = 5;
        }
        //获取书籍图片的路劲
        String imgSrc = searchElement.getElementsByTag("img").attr("abs:src");
        //获取书籍名
        String audioName = search.getElementsByClass("xm-album-title ellipsis-2").first().attr("title");
        //获取简介
        String summary = getSummary(bookId);

        String fileName = System.currentTimeMillis() + ".jpg";
        TableAudioName tableAudioName = TableAudioName.builder()
                .allTypeId(allTypeId)
                .audioNameName(audioName)
                .audioNameSummary(summary)
                .uId(tableUser.getUId())
                .audioTypeId(audioTypeId)
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
                .userAgent(getUa())
                .ignoreContentType(true)
                .timeout(3 * 1000)
                .execute();
        BufferedInputStream bufferedInputStream = response.bodyStream();
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

    /**
     * 抓取随机ua
     *
     * @return
     */
    private String getUa() {
        int i = new Random().nextInt(ua.length);
        return ua[i];
    }

    /**
     * 随机抓取访问ip
     *
     * @return
     */
    private String getAgentService() {
        int i = new Random().nextInt(agentServices.length);
        return agentServices[i];
    }

    @Override
    public void xmlyReptile(String name, TableUser tableUser, int audioTypeId) {
        try {
            this.reptileAudio(name, audioTypeId, tableUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}