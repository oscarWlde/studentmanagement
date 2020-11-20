package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.Take_map;
import com.example.demo.service.Load_class_service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@Tag(name = "class", description = "the classFile API")
public class ClassController {

    @Autowired
    private Load_class_service service;

    @Operation(summary = "load class time table", description = "", tags = { "class" })
    @Cacheable(value= "classCache", key= "#p0")
    @RequestMapping("/student/home/time")//加载选择课程的时间，实现降序排列（成功）
    public JSONObject load_home_time(@RequestBody String json){
        System.out.println(json);       //ajax 对指定路有传参
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.load_course_time(jsonObject.getString("id"),jsonObject.getString("type"));
    }

    @Operation(summary = "select class time table", description = "", tags = { "class" })
    @RequestMapping("/selected_courseTable")
    public JSONObject load_already_selected(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);

        return service.load_already_selected(jsonObject.getString("Id"),jsonObject.getString("type"),jsonObject.getString("year"),jsonObject.getString("semester"));
    }

    @Operation(summary = "load class", description = "", tags = { "class" })
    @RequestMapping("/student/load/course")//加载学生可以选择的全部课程（成功）
    public JSONObject load_course_load(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(service.load_course_load(jsonObject.getString("id"),jsonObject.getString("type")));
        return service.load_course_load(jsonObject.getString("id"),jsonObject.getString("type"));
    }

    @Operation(summary = "select class time table", description = "", tags = { "class" })
    @RequestMapping("/student/search/course")//查询课程，按照指定年份和季度（成功）
    public JSONObject search_course(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.search_course(jsonObject.getString("year"),jsonObject.getString("semester"));
    }

    @Operation(summary = "select class", description = "", tags = { "class" })
    @RequestMapping("/student/select/course")//选课信息加载，限制了限选年级（成功）
    public JSONObject load_select_course(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(service.load_select_course(jsonObject.getString("id"),jsonObject.getString("state"),jsonObject.getString("type"),jsonObject.getString("academyName")));
        return service.load_select_course(jsonObject.getString("id"),jsonObject.getString("state"),jsonObject.getString("type"),jsonObject.getString("academyName"));
    }

    @Operation(summary = "refresh insert class", description = "", tags = { "class" })
    @RequestMapping("/student/refresh/inserted/information")//刷新已选课程(成功）校验已经插入
    public JSONObject refresh_inserted_information(@RequestBody String json){
        JSONArray array=JSONArray.parseArray(json);
        JSONObject res = new JSONObject();
        res.put("data","ok");
        for(int i=0;i<array.size();i++){
           // service.updateproject(array.getJSONObject(i).getString("projectId"));
            service.refresh_inserted_course(array.getJSONObject(i).getString("stuId"),array.getJSONObject(i).getString("courseNum"),array.getJSONObject(i).getString("year"),array.getJSONObject(i).getString("semester"),array.getJSONObject(i).getString("state"));
        }
        return res;
        //将date划分为学期和学年

    }

    @Operation(summary = "delete class", description = "", tags = { "class" })
    @RequestMapping("/student/deletesc")
    public JSONObject delete(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject res =new JSONObject();
        res.put("code","delete");

        service.deletesc(jsonObject.getString("stuId"),jsonObject.getString("courseNum"),jsonObject.getString("year"),jsonObject.getString("semester"));
        return res;
    }
}
