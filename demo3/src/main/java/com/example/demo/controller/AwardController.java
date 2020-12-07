package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.award_service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwardController {
    @Autowired
    private award_service service;
    @RequestMapping("/student/award")//查询学生已申请的奖学金
    public JSONObject load_selected_ward(@RequestBody String json)
    {
       System.out.println(json);
     JSONObject jsonObject = JSONObject.parseObject(json);
       System.out.println( service.load_award(jsonObject.getString("Id")));
        return service.load_award(jsonObject.getString("Id"));
      //  return service.load_award("2019001");
    }

    @RequestMapping("/student/allaward")//查询所有学生奖项成功
    public JSONObject load_award()
    {
        return service.load_all_award();
    }

    @RequestMapping("/select_award/student")
    public JSONObject select_award(@RequestBody String json)
    {
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(json);
        System.out.println(service.get_award("1",jsonObject.getString("state")));
        return service.get_award("1",jsonObject.getString("state"));
    }


    @RequestMapping("/student/insertAward")//申请奖学金
    public JSONObject insert_award(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);

        return service.insert_awrad(jsonObject.getString("stuId"),"1",jsonObject.getString("awardName"),"0",jsonObject.getString("descript"),jsonObject.getString("time"));
}
    @RequestMapping("/student/updateAward")
    public JSONObject update_award(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return service.update_award(jsonObject.getString("stuId"),jsonObject.getString("state"),jsonObject.getString("awardName"),jsonObject.getString("time"));

    }
}