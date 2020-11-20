package com.example.demo.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
@Service
public interface Sc_service {
    JSONObject load_selected_courseTable(String id, String type,String nowyear,String semester);

    JSONObject search_select_course(String id);

    JSONObject search_today(String stuId, String semester, String weekdata);
}
