package com.lightseablue.bookwebsite.controller;

import com.lightseablue.bookwebsite.entity.TableUser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: EmailController
 * @Package: com.lightseablue.bookwebsite.controller
 * @Description:邮件发送处理
 * @author: LightseaBlue
 * @date: 2021/4/24     22:08
 */
@Controller
public class EmailController {

    @PostMapping("/toAdmin")
    @ResponseBody
    public Integer toAdmin(HttpServletRequest request, String topicVal, String detailVal) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage message = new SimpleMailMessage();
        TableUser user = (TableUser) request.getSession().getAttribute("user");
        message.setSubject(topicVal);
        message.setText("用户:" + user.getUName() + "对本站提出意见,请处理........\n" + detailVal);
        //todo: 这里是自己给自己发,应该是用户给发.
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
}
