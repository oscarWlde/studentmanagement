package com.example.demo.implement;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Classroom;
import com.example.demo.bean.Take;
import com.example.demo.mapper.Classroom_map;
import com.example.demo.mapper.Take_map;
import com.example.demo.service.Classroom_service;
import com.example.demo.util.classroom.room_state;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClassroomImplement implements Classroom_service {
    @Autowired
    Classroom_map classroom_map;
    @Autowired
    Take_map take_map;
    @Override
    public JSONObject load_classroom(String id,String type,String semester,String week){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        JSONObject result = new JSONObject();
        List<room_state> temp = new ArrayList<>();
        List<Classroom> classrooms = classroom_map.get_classroom();
        List<Take> takes = take_map.search_classroom1(year,week,semester);
        for(int i = 0;i<classrooms.size();i++)
        {
            room_state temproom=new room_state(classrooms.get(i).getClassroomId());
            temproom.change_state(takes);
            temp.add(temproom);
        }
        result.put("data",temp);
        return result;
    }

    @Override
    public JSONObject load_classroom1(String id,String type,String semester,String week,String buildingName)
    {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));//默认日期为当前系统日期
        JSONObject result = new JSONObject();
        List<room_state> temp = new ArrayList<>();
        List<Classroom> classrooms = classroom_map.search_classroom(buildingName);//获得目标教学楼所有教室
        List<Take> takes = take_map.search_classroom3(buildingName,week,year,semester);//获得目标的教室状态
        for(int i = 0;i<classrooms.size();i++)
        {
            room_state temproom=new room_state(classrooms.get(i).getClassroomId());
            temproom.change_state(takes);
            temp.add(temproom);
        }
        result.put("data",temp);
        return result;
    }
    @Override
    public JSONObject apply_classroom(Take t)
    {
        JSONObject result = new JSONObject();
//        if (t.getYear()==null)
//        {
//            Calendar date = Calendar.getInstance();
//            String year = String.valueOf(date.get(Calendar.YEAR));
//            t.setYear(year);
//        }
        System.out.println(t.toString());
        take_map.addtake(t);
        result.put("check","true");
        return result;
    }

    @Override
    public JSONObject selected_classroom(String id,String type)
    {
        JSONObject result = new JSONObject();
        List<Take> a = take_map.select_classroom(id,type);
        result.put("data",a);
        return result;
    }

//    @Override
//    public JSONObject search_specclassroom(String id,String type,String year,String week,String building,String classroomid)
//    {
//        JSONObject result = new JSONObject();
//        List<room_state> temp = new ArrayList<>();
//        System.out.println(building);
//        List<Classroom> classrooms = classroom_map.search_classroom(building);
//        List<Take> takes = take_map.search_classroom(building,week,year);
//        for(int i = 0;i<classrooms.size();i++)
//        {
//            room_state temproom=new room_state(classrooms.get(i).getClassroomId());
//            temproom.change_state(takes);
//            temp.add(temproom);
//        }
//        result.put("data",temp);
//        return result;
//    }

    @Override
    public JSONObject search_selected_classroom(String id,String type,String year,String week,String building,String classroomid){
        JSONObject result = new JSONObject();
        List<Take> a = take_map.search_classroom(id,classroomid,year,week);
        result.put("data",a);
        return result;
    }

    @Override
    public JSONObject cancel_apply(String takeid,String classroomId,String takeDate,String start_time,String end_time,String week,String day)
    {
        JSONObject result = new JSONObject();
        int a = take_map.delete(takeid,classroomId,takeDate,start_time,end_time,week,day);
        System.out.println(a);
        if (a==0)
        {
            result.put("check","false");
            return result;
        }
        else{
            result.put("check","true");
            return result;
        }
    }
}
