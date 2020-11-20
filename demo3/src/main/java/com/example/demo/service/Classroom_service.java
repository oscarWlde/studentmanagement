package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Take;
import org.springframework.stereotype.Service;
@Service
public interface Classroom_service {

    JSONObject load_classroom(String id, String type,String semester,String week);

    JSONObject load_classroom1(String id,String type,String semester,String week,String buildingName);

    JSONObject apply_classroom(Take t);

    JSONObject selected_classroom(String id, String type);

    JSONObject search_selected_classroom(String id, String type, String year, String week, String building, String classroomid);

    //JSONObject search_specclassroom(String id, String type, String year, String week, String building, String classroomid);

    JSONObject cancel_apply(String takeid, String classroomId, String takeDate, String start_time, String end_time, String week, String day);
}
