package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.SC_map;
import com.example.demo.service.Sc_service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.stereotype.Controller;

@RestController
public class ScController {
    @Autowired
    SC_map sc_map;

    @Autowired
    Sc_service sc_service;

    @RequestMapping("/load_selected_courseTable")
    public JSONObject load_selected_courseTable1(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);

        System.out.println( json);
        System.out.println( sc_service.load_selected_courseTable(jsonObject.getString("Id"),jsonObject.getString("type"),jsonObject.getString("year"),jsonObject.getString("semester")));
        return sc_service.load_selected_courseTable(jsonObject.getString("Id"),jsonObject.getString("type"),jsonObject.getString("year"),jsonObject.getString("semester"));

    }

    @RequestMapping("/search_select_course")

    public JSONObject search_select_course(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return sc_service.search_select_course(jsonObject.getString("id"));
    }

    @RequestMapping("/search_today")
    public JSONObject search(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);

        return sc_service.search_today(jsonObject.getString("id"), jsonObject.getString("semster"), jsonObject.getString("weekdata"));

    }
}
