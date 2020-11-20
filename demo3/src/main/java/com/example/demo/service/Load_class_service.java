package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface Load_class_service {
    JSONObject load_course_time(String id, String type);//课程1号功能

    JSONObject load_course_load(String id, String type);//课程2号功能

    JSONObject search_course(String year, String semster);

    JSONObject load_select_course(String id, String state, String type,String academy);

    JSONObject load_selected_course(String id,String state,String type,String academy);

    void refresh_inserted_course(String stuId, String courseNum, String myyear, String semster,String state);

    void deletesc(String stuId,String courseNum,String myyear,String semster);

    JSONObject load_already_selected(String id,String type,String year,String semester);
}
