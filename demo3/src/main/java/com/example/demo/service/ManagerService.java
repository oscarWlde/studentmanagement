package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Student;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {
    JSONObject managergrade(String year, String semster, String academyName, String limitGrade);

     JSONObject insertC(String courseId, String courseOrder, String courseNum, String courseName, String courseMemberNum, String capacity, String courseTotalTimes, String credit, String semster, String type, String limitGrade, String time, String language, String year, String weekdata, String teacherName, String information);

    JSONObject insert_student(Student student);

    JSONObject change_class(String stuId, String classId, String oldclassId);

    JSONObject grade(String academyName,String year,String semster);

    JSONArray managerall_the_project(String state);

    JSONObject certain_year_gpa(String academy,String grade,String year);

    JSONObject managerloadCourse(String semster,String year, String academyName,String grade);

    JSONObject deleteC(String semster,String year,String courseOrder,String courseId,String takeId);

    JSONObject warning(String year);
}
