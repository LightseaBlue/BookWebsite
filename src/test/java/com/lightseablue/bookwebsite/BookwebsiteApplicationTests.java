package com.lightseablue.bookwebsite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootTest
class BookwebsiteApplicationTests {


    @Autowired
    XmlyReptile xmlyReptile;
    @Test
    public void contextLoads() throws SQLException, SQLException, IOException, InterruptedException {
//        TableUser tableUser = tableUserService.getOne(new QueryWrapper<TableUser>().lambda().eq(TableUser::getUName, "LightseaBlue"));
//        xmlyReptile.reptileAudio("路上有狼", 1, tableUser);
//        xmlyReptile.reptileAudio("《夜色钢琴曲》", 12, tableUser);
//        Thread.sleep(1000);
//        xmlyReptile.reptileAudio("总有这样的歌只想一个人听", 11, tableUser);
//        xmlyReptile.reptileAudio("雨声催眠 大自然的声音", 12, tableUser);
//        xmlyReptile.reptileAudio("郭德纲21年相声精选", 7, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("德云社相声精选集", 7, tableUser);
//        xmlyReptile.reptileAudio("BBC随身英语", 13, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("聆听民间故事", 14, tableUser);
//        xmlyReptile.reptileAudio("读者", 15, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("西方艺术史", 15, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("英语儿歌磨耳朵", 18, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("怪奇侦探团", 19, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("父与子全集", 20, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("宝宝巴士经典儿歌", 21, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("米小圈上学记", 22, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("葫芦兄弟全集", 23, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("齐俊杰看财经", 24, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("雷科技", 25, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("周文强财商思维讲堂", 26, tableUser);
//        Thread.sleep(10000);
//        xmlyReptile.reptileAudio("掘金东南亚跨境电商Shopee", 27, tableUser);

    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis() throws JsonProcessingException {
        TableAllTypes tableAllTypesBuilder = TableAllTypes.builder().allTypeId(1).build();

        String s = new ObjectMapper().writeValueAsString(tableAllTypesBuilder);
        redisTemplate.opsForValue().set("mdy",s);
        System.out.println(redisTemplate.opsForValue().get("mdy"));
    }

    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void testSpringBootRedis() throws JsonProcessingException {
        TableAllTypes tableAllTypes = TableAllTypes.builder().allTypeId(1).build();

        boolean test = redisUtil.hset("test","mdy", tableAllTypes);
        System.out.println(test);
        TableAllTypes hget = (TableAllTypes) redisUtil.hget("test", "mdy");
        System.out.println(hget);
    }

    @Test
    public void testUrl(){
        String a="/";
        File f=new File(a);
        System.out.println(f.getAbsolutePath());
    }
}
