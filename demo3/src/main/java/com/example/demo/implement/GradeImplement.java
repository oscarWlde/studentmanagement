package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.Grade_mapper;
import com.example.demo.service.Load_grade_service;
import com.example.demo.util.student.return_grade;
import com.example.demo.util.student.return_search_grade;
import com.example.demo.util.student.return_totalcredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeImplement implements Load_grade_service {

    @Autowired
    private Grade_mapper mapper;

    @Override//查询指定id的分(成功）
    public JSONObject load_grade(String id, String type) {
        List<return_grade> get_grades = mapper.load_grade(id);
        JSONObject res = new JSONObject();
        if(get_grades!=null){
            res.put("data",get_grades);
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }

    @Override//查询指定年份的分（成功）
    public JSONObject search_grade(String id,String year,String semster) {
        List<return_search_grade> search_grades = mapper.search_grade(id, year,semster);
        JSONObject res = new JSONObject();
        if (search_grades != null) {
            res.put("data", search_grades);
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }

    @Override
    public JSONObject load_totalCredit(String id, String year) {
        List<return_totalcredit> total_grades = mapper.search_credit(id);
        JSONObject res = new JSONObject();
        if (total_grades != null) {
            res.put("data",total_grades );
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }
}
