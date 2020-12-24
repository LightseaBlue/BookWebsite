package com.lightseablue.bookwebsite;

import com.lightseablue.bookwebsite.entity.TableAllTypes;
import com.lightseablue.bookwebsite.reptile.XmlyReptile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootTest
class BookwebsiteApplicationTests {

    //注入数据源
    TableAllTypes types=new TableAllTypes();

    @Autowired
    XmlyReptile xmlyReptile;

    @Test
    public void contextLoads() throws SQLException, SQLException, IOException {
       xmlyReptile.reptileAudio("小王子");
    }



}
