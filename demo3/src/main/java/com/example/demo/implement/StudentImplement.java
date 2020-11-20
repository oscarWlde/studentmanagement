package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Student;
import com.example.demo.mapper.Student_mapper;
import com.example.demo.mapper.Teacher_mapper;
import com.example.demo.service.student_service;
import com.example.demo.util.teacher.return_today_course;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentImplement implements student_service {
    @Autowired
    Student_mapper mapper;

    @Override//加载今天的课程
    public JSONObject load_stu_today(String id, String weekdata) {
        JSONObject res = new JSONObject();
        List<return_today_course> today_courses =mapper.load_stu_course(id,weekdata);
        if (today_courses!=null){
            res.put("data",today_courses);
            return res;
        }
        else{
            res.put("check",false);
            return res;
        }
    }


}
