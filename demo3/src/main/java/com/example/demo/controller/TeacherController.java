package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.Load_class_service;
import com.example.demo.service.Teacher_service;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//真正写的时候整合一起改，否则自己很难调
@RestController
public class TeacherController {

    @Autowired
    private Teacher_service service;

    @Autowired
    private ManagerService m_service;


    @RequestMapping("/teacher/today/schedule")//查询老师当天要选的课（成功）
    public JSONObject LOAD_TODAY(@RequestBody String json) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.load_today(jsonObject.getString("id"),getWeek());

    }

    @RequestMapping("/teacher/select/course")//加载老师要选的课(成功）
    public JSONObject select_data(@RequestBody String json){
        JSONObject res = new JSONObject();
        res.put("message","not found");
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        if(jsonObject.getString("signal").equals("1")){
            System.out.println("1");
            System.out.println(service.select_my_data1(jsonObject.getString("year"),jsonObject.getString("semester")));
            return service.select_my_data1(jsonObject.getString("year"),jsonObject.getString("semester"));
        }else if(jsonObject.getString("signal").equals("2")){
            System.out.println(service.select_my_data2(jsonObject.getString("teacherId"),jsonObject.getString("year"),jsonObject.getString("semester")));
            return service.select_my_data2(jsonObject.getString("teacherId"),jsonObject.getString("year"),jsonObject.getString("semester"));
        }
        else {
            return res;
        }

    }
    @RequestMapping("/student/select/teacher/course/upp")
    public JSONObject upp(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
       System.out.println(json);
        return service.upp(jsonObject.getString("courseOrder"),jsonObject.getString("courseId"),jsonObject.getString("semester"),jsonObject.getString("year"),jsonObject.getString("teacherId"));
    }

    @RequestMapping("/teacherCourseTable")
    public JSONObject selected_courses(@RequestBody String json)
    { JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return service.Selected_courses(jsonObject.getString("tId"),jsonObject.getString("semester"),jsonObject.getString("year"));
    }


    @RequestMapping("/teacher/selected/course")//老师要选的课
    public JSONObject selected_data(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy");
        return service.select_history_data(jsonObject.getString("id"),sim.format(new Date()));
    }


    @RequestMapping("/teacher/insert/course")//将选的课插入到数据库中（成功）
    public JSONObject insert_selected_course(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy");

        String str_courseTime[] = jsonObject.getString("courseTime").split(" ");
        System.out.println(str_courseTime[0]);
        String str_courseNum[] = jsonObject.getString("courseNum").split("-");
        System.out.println(str_courseNum[0]);
        return service.insert_selected_course( jsonObject.getString("courseNum") ,
                                                jsonObject.getString("courseName"),
                                                str_courseNum[0],
                                                str_courseNum[1],
                                                jsonObject.getString("type"),
                                                str_courseTime[0],
                                                str_courseTime[1],
                                                sim.format(new Date()),
                                                jsonObject.getString("courseMemberNum"),
                                                jsonObject.getString("semster"),
                                                sim.format(new Date())
        );
    }
    @RequestMapping("/teacher/return/normal")
    public JSONObject returnormal(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(service.returnormal(
                jsonObject.getString("teacherId"), jsonObject.getString("year"),
                jsonObject.getString("semester"),jsonObject.getString("courseNum")));
        return service.returnormal(
                jsonObject.getString("teacherId"), jsonObject.getString("year"),
                jsonObject.getString("semester"),jsonObject.getString("courseNum"));
    }

    @RequestMapping("teacher/update/grade")
    public JSONObject updategrade(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(service.updategrade(jsonObject.getString("stuId"), jsonObject.getString("courseNum"), jsonObject.getString("year"),jsonObject.getString("semester"),jsonObject.getString("pgrade"),jsonObject.getString("grade"),jsonObject.getString("totalgrade")));
        return service.updategrade(jsonObject.getString("stuId"), jsonObject.getString("courseNum"), jsonObject.getString("year"),jsonObject.getString("semester"),jsonObject.getString("pgrade"),jsonObject.getString("grade"),jsonObject.getString("totalgrade"));
    }

    @RequestMapping("/teacher/deleteC")
    public JSONObject managerdelete(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return m_service.deleteC(jsonObject.getString("semster"),jsonObject.getString("year"),jsonObject.getString("courseOrder"),jsonObject.getString("courseId"),jsonObject.getString("takeId"));
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
