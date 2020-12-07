package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Take;
import com.example.demo.mapper.Take_map;
import com.example.demo.service.Classroom_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class ClassroomController {

    @Autowired
    Classroom_service service;
    @Autowired
    Take_map take;
    @RequestMapping("/load_Classroom")//测试（成功）
    public JSONObject load_classroom(@RequestBody String json ){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        if (jsonObject.getString("buildingName").equals(" 全部"))
        {     System.out.println(service.load_classroom(jsonObject.getString("id"),jsonObject.getString("type"),"2",jsonObject.getString("week")));
            return service.load_classroom(jsonObject.getString("id"),jsonObject.getString("type"),"2",jsonObject.getString("week"));}
        else {
            System.out.println(service.load_classroom1(jsonObject.getString("id"),jsonObject.getString("type"),"2",jsonObject.getString("week"),jsonObject.getString("buildingName")));
            return service.load_classroom1(jsonObject.getString("id"),jsonObject.getString("type"),"2",jsonObject.getString("week"),jsonObject.getString("buildingName"));
        }
    }
    @RequestMapping("/applyClassroom")//已经修改
    public JSONObject apply_classroom(@RequestBody String json) throws ParseException {
        System.out.println(json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Take t = new Take();
        t.setYear(jsonObject.getString("year"));
        t.setTakeId(jsonObject.getString("id"));
        t.setTakeDate(jsonObject.getString("data"));
        t.setDay(jsonObject.getString("day"));
        t.setStartTime(jsonObject.getString("starttime"));
        t.setInformation(jsonObject.getString("information"));
        t.setEndTime(jsonObject.getString("endtime"));
        t.setType(jsonObject.getString("type"));
        t.setClassroomId(jsonObject.getString("classroomid"));
        String week = getweek(jsonObject.getString("data"));
        t.setWeek(week);
        t.setSemster(jsonObject.getString("semester"));
        System.out.println(t.toString());

        return service.apply_classroom(t);
    }


    @RequestMapping("/Classroom/load/selected/classroom")//测试（成功）
    public JSONObject load_selected_classroom(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.selected_classroom(jsonObject.getString("id"),jsonObject.getString("type"));
    }

    @RequestMapping("/Classroom/search/selected/classroom")//测试（成功）
    public JSONObject search_selected_classroom(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.search_selected_classroom(jsonObject.getString("id"),jsonObject.getString("type"),jsonObject.getString("year"),jsonObject.getString("week"),jsonObject.getString("building"),jsonObject.getString("classroomid"));
    }

//    @RequestMapping("/Classroom/search/classroom")//测试（成功）
//    public JSONObject serach_classroom(@RequestBody String json)
//    {
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        return service.search_specclassroom(jsonObject.getString("id"),jsonObject.getString("type"),jsonObject.getString("year"),jsonObject.getString("week"),jsonObject.getString("building"),jsonObject.getString("classroomid"));
//    }

    @RequestMapping("/Classroom/cancel/apply")//测试（成功）：说明没有此申请
    public JSONObject cancel_apply(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.cancel_apply(jsonObject.getString("Id"),jsonObject.getString("classroomid"),jsonObject.getString("takeDate"),jsonObject.getString("startTime"),jsonObject.getString("endTime"),jsonObject.getString("week"),jsonObject.getString("day"));
    }

    //---------------------------get week---------------------------//
    public  String getweek(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        String smester1 = "2020-06-30";
        String smester2 = "2020-09-01";

        long between_days = 0;

        if(compareDate(sdf.parse(date),sdf.parse(smester2))){
            cal.setTime(sdf.parse(smester2));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24)/7;
            if(((time2-time1)/(1000*3600*24)%7) != 0 ) {
                between_days++;
            }
        }

        else if(compareDate(sdf.parse(smester1),sdf.parse(date))) {
            cal.setTime(sdf.parse("2020-03-01"));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24)/7;
            if(((time2-time1)/(1000*3600*24)%7) != 0 ) {
                between_days++;
            }
        }

        return String.valueOf(between_days);
    }

    public boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }

}
