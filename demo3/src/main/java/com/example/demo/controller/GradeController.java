package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.Load_grade_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {

    @Autowired
    private Load_grade_service service;

    @RequestMapping("/student/grade")//查找学生全部成绩（成功）
    public JSONObject load_home_time(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.load_grade(jsonObject.getString("stuId"),jsonObject.getString("type"));
    }

    @RequestMapping("/student/search/grade")//查找改年的分数（成功）
    public JSONObject search_grade(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(service.search_grade(jsonObject.getString("id"),jsonObject.getString("year"),jsonObject.getString("semster")));
        return service.search_grade(jsonObject.getString("id"),jsonObject.getString("year"),jsonObject.getString("semster"));
    }

    @RequestMapping("/student/totalCredit")//查询对应学分（成功）
    public JSONObject load_totalCredit(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.load_totalCredit(jsonObject.getString("stuId"),jsonObject.getString("type"));
    }



}
