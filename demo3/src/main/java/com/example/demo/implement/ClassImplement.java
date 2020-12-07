package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Sc;
import com.example.demo.mapper.Class_mapper;
import com.example.demo.mapper.SC_map;
import com.example.demo.service.Load_class_service;
import com.example.demo.util.sc.course_return;
import com.example.demo.util.student.return_date;
import com.example.demo.util.student.return_student_course;
import com.example.demo.util.student.return_select_course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassImplement implements Load_class_service {
    @Autowired
    private Class_mapper mapper;

    @Autowired
    private SC_map sc_map;

    @Override//加载时间
    public JSONObject load_course_time(String id, String type) {
        List<return_date> date_time = mapper.gettime();
        JSONObject res = new JSONObject();

        if(date_time!=null) {
           // res.put("check",0);
            res.put("data", date_time);
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }

    @Override//加载课程
    public JSONObject load_course_load(String id, String type) {
        List<return_student_course> get_courses = mapper.getmyClass();
        JSONObject res = new JSONObject();

        if(get_courses!=null){
            res.put("data",get_courses);
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }

    @Override//搜索课程
    public JSONObject search_course(String year, String semster) {
        List<return_student_course> get_courses = mapper.getregularClass(year, semster);
        JSONObject res = new JSONObject();

        if(get_courses!=null){
            res.put("data",get_courses);
            return res;
        }
        else{
            res.put("code",404);
            return res;
        }
    }

    @Override
    public JSONObject load_select_course(String id, String state, String type,String academyName) {
        String limitgrade = mapper.return_limit_grade(id);
        System.out.println(limitgrade);
        JSONObject res = new JSONObject();
        if(state.equals("1")){
                List<return_select_course> load_select_courses = mapper.get_select_course(limitgrade,type,academyName);

                res.put("data1",load_select_courses);
            }else if(state.equals("2")){
                state="1";

            }
            List<return_select_course> loadstu = mapper.get_selected_course(id,state,type,academyName);
            res.put("check",loadstu);

            return res;


    }

    @Override
    public JSONObject load_selected_course(String id,String state,String type,String academy) {;
        List<return_select_course> load_selected_courses = mapper.get_selected_course(id,state,type,academy);
        JSONObject res = new JSONObject();
        if(load_selected_courses!=null){
            res.put("data",load_selected_courses);
            return res;
        }
        else{
            res.put("message","no class found");
            return res;
        }
    }

    @Override
    public void refresh_inserted_course(String stuId, String courseNum, String myyear, String semster,String state) {
        Sc sc = new Sc();
        sc.setStuId(stuId);
        sc.setCourseNum(courseNum);
        sc.setMyyear(myyear);
        sc.setSemster(semster);
        sc.setState(state);
        sc.setPgrade(null);
        sc.setGrade(null);
        sc.setTotalgrade(null);
        mapper.refresh_inserted_course(sc);

    }

    @Override
    public JSONObject load_already_selected(String id,String type,String year,String semester)
    {
        List<course_return> a = sc_map.ultimate_coursetable(id,type,year,semester);
        JSONObject res = new JSONObject();
        res.put("data",a);
        return res;
    }
    @Override
    public void deletesc(String stuId, String courseNum, String myyear, String semster) {
        mapper.deletesc( stuId,  courseNum, myyear,  semster);
    }


}
