package com.example.demo;

import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
    @Autowired
    MailService mailService;

    @Test
    public void sendSimplemail() {
        mailService.sendSimpleMail("839891341@qq.com","这是一封测试邮件","内容为文本格式");
    }



}