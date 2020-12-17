package com.lightseablue.bookwebsite;

import com.lightseablue.bookwebsite.entity.TableAllTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class BookwebsiteApplicationTests {

    //注入数据源
    TableAllTypes types=new TableAllTypes();

    @Test
    public void contextLoads() throws SQLException, SQLException {
        List<TableAllTypes> tableAllTypes = types.selectAll();
        tableAllTypes.forEach(System.out::println);
    }


}
