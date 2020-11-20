package com.example.demo.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
@Service
public interface Project_service {
    JSONObject create_project(String projectId, String projectName, String type, String teacherId, long studentNum,String startTime,String endTime);

    JSONObject personal_project(String StuId);

    JSONArray all_the_project(String teacherId,String state);

    void updateproject(String projectId);

    JSONObject allStuproject();

    JSONArray stusearch(String stuId, String state);

    void insertPro(String projectId, String stuId, String state, String mylevel, String applytime);

    JSONObject search_student(String stuId);

    void judgeppp(String projectId,String stuId,String state,String mylevel,String applytime);

    JSONObject sssearchacademy(String academyName);

    JSONObject allsss();
}
