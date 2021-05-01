package com.lightseablue.bookwebsite.controller;

import com.lightseablue.bookwebsite.entity.TableUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: EmailController
 * @Package: com.lightseablue.bookwebsite.controller
 * @Description:邮件发送处理
 * @author: LightseaBlue
 * @date: 2021/4/24     22:08
 */
@RestController
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/toAdmin")
    public Integer toAdmin(HttpServletRequest request, String topicVal, String detailVal) {

        SimpleMailMessage message = new SimpleMailMessage();
        TableUser user = (TableUser) request.getSession().getAttribute("user");
        message.setSubject(topicVal);
        message.setText("用户:" + user.getUName() + "对本站提出意见,请处理........\n" + detailVal);
        message.setTo("1172893066@qq.com");
        message.setFrom("1172893066@qq.com");
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
        return 1;
    }

    @PostMapping("getCode")
    private Integer getCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("马老四听书");
        int num = (int) (Math.random() * 9000 + 1000);
        message.setText("您好!\n" + "本次验证码为:" + num);
        message.setTo("1172893066@qq.com");
        message.setFrom(email);
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
        return num;
    }
}
