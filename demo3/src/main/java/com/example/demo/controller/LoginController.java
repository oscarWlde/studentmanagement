package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.Login_user_service;

import com.example.demo.service.MailService;
import com.example.demo.service.student_service;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class LoginController {

    @Autowired
    private Login_user_service service;

    @Autowired
    MailService mailService;


    //登录窗口判别身份和进行认证
    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    public JSONObject login_1(@RequestBody String json, HttpServletRequest request) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date currenttime1 = calendar.getTime();
        String currenttime = sdf.format(currenttime1);
        HttpSession session = request.getSession();
        if(session.getAttribute("timein")==null){session.setAttribute("timein",currenttime);}
        else{String nowtime= (String) session.getAttribute("timein");Date nowtime1 = sdf.parse(nowtime);
        long diff = nowtime1.getTime() - currenttime1.getTime();
        long stayminutes= diff / (1000 * 60);}
        JSONObject wrong_message = new JSONObject();
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json.toString());
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(jsonObject.getString("name"), jsonObject.getString("psw"));
        // 执行认证登陆
        ((org.apache.shiro.subject.Subject) subject).login(token);


        if(jsonObject.getString("person").equals("student")){
            return service.log_my_stu(jsonObject.getString("name"),jsonObject.getString("psw"),jsonObject.getString("person"));
        }
        else if (jsonObject.getString("person").equals("teacher")){
            return service.log_my_tea(jsonObject.getString("name"),jsonObject.getString("psw"),jsonObject.getString("person"));
        }
        else if(jsonObject.getString("person").equals("manager")){
            System.out.println(service.log_my_manager(jsonObject.getString("name"),jsonObject.getString("psw"),jsonObject.getString("person")));
            return service.log_my_manager(jsonObject.getString("name"),jsonObject.getString("psw"),jsonObject.getString("person"));
    }
        wrong_message.put("message","none of this person");
        return wrong_message;
    }

    @RequestMapping("/student/today/scheduleeee")//查询学生当天要上的课
    public JSONObject info(@RequestBody String json) throws ParseException {
        System.out.println("enter");
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(service.loadinfo(jsonObject.getString("Id")));
        return service.loadinfo(jsonObject.getString("Id"));
    }

    @RequestMapping("/email")
    public JSONObject email(){
        JSONObject res = new JSONObject();
        mailService.sendSimpleMail("2019001","这是一封测试邮件","839891341@qq.com");
        res.put("data","sented");
        return res;
    }

}
