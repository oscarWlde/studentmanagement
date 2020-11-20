package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.Teacher_service;
import com.example.demo.service.student_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class StudentController {
    @Autowired
    private student_service service;


    @RequestMapping("/teacher/today/schedule")//查询学生当天要上的课
    public JSONObject LOAD_TODAY(@RequestBody String json) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.load_stu_today(jsonObject.getString("id"),getWeek());

    }




    private String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "7";
        } else if (weekday == 2) {
            week = "1";
        } else if (weekday == 3) {
            week = "2";
        } else if (weekday == 4) {
            week = "3";
        } else if (weekday == 5) {
            week = "4";
        } else if (weekday == 6) {
            week = "5";
        } else if (weekday == 7) {
            week = "6";
        }
        return week;
    }

}
