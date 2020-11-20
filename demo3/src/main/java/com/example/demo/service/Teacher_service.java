package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface Teacher_service {
    JSONObject load_today(String id, String weekdata);

    JSONObject select_my_data1(String id, String semster);

    JSONObject insert_selected_course(String id,
                                      String courseName,
                                      String courseNum,
                                      String courseId,
                                      String courseOrder,
                                      String type,
                                      String weekdata,
                                      String time,
                                      String courseMemberNum,
                                      String semster, String year);

    JSONObject select_history_data(String id, String format);

    JSONObject select_my_data2(String teacherId, String year, String semester);

    JSONObject upp(String courseOrder,String courseId,String semester,String year,String teacherId);

    JSONObject Selected_courses(String tId,String semester,String year);

    JSONObject returnormal(String teacherId,String year,String semester,String courseNum);

    JSONObject updategrade(String stuId,String courseNum,String year,String semester,String pgrade,String grade,String totalgrade);
}
