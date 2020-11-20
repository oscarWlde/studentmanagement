package com.example.demo.implement;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Course;
import com.example.demo.bean.Sc;
import com.example.demo.mapper.SC_map;
import com.example.demo.service.Sc_service;
import com.example.demo.util.sc.course_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
@Service
public class ScImplement implements Sc_service {

    @Autowired
    SC_map sc_map;

    @Override
    public JSONObject load_selected_courseTable(String id,String type,String nowyear,String semester)
    {
        JSONObject result = new JSONObject();
        List<course_return> a = sc_map.ultimate_coursetable(id,type,nowyear,semester);

        result.put("data",a);
        return result;
    }

    @Override
    public JSONObject search_select_course(String id)
    {
        Calendar date = Calendar.getInstance();
        String nowyear = String.valueOf(date.get(Calendar.YEAR));
        JSONObject result = new JSONObject();
        List<Sc> a = sc_map.search_select_course(id);
        result.put("data",a);
        return result;
    }

    public void change_model(List<Course> course)
    {
        String memberNum = " ";
        for(int i = 0;i<course.size();i++)
        {
            memberNum = String.valueOf(course.get(i).getCourseMemberNum());

        }
    }

    public JSONObject search_today(String stuId,String semester,String weekdata){
        Calendar date = Calendar.getInstance();
        String nowyear = String.valueOf(date.get(Calendar.YEAR));
        JSONObject result = new JSONObject();
        List<Course> a = sc_map.search_today(nowyear,semester,weekdata,stuId);
        result.put("data",a);
        return result;
    }

}
