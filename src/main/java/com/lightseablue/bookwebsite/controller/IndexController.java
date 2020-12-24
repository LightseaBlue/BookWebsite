package com.lightseablue.bookwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Program: bookwebsite
 * @ClassName: IndexController
 * @Author: LightseaBlue
 * @Date: 2020-12-18 21:01
 * @Version: V1.0
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }

    @GetMapping("/user")
    public String getUser() {
        return "/view/user";
    }

    @GetMapping("/user2")
    public String getUser2() {
        return "/view/user2";
    }

//    @GetMapping("/login")
//    public String getLogin(){
//        return "";
//    }
}
