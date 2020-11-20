package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Awardpunishment;
import com.example.demo.mapper.Award_mapper;
import com.example.demo.service.award_service;
import com.example.demo.util.award.return_award;
import com.example.demo.util.award.select_award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardImplement implements award_service {
    @Autowired
    private Award_mapper mapper;

    @Override
    public JSONObject load_award(String id) {
        JSONObject res = new JSONObject();
        List<return_award> award = mapper.award(id);
        if(award!=null) {
            res.put("data", award);
            return res;
        }else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONObject load_all_award() {
        JSONObject res = new JSONObject();
        List<Awardpunishment> award = mapper.get_allaward();
        if(award!=null) {
            res.put("data", award);
            return res;
        }else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONObject insert_awrad(String stuId,String tag,String awardName,String state,String descript,String time){
        JSONObject res = new JSONObject();
        int a = mapper.insert_award(stuId,awardName,tag,state,descript,time);
        if(a==1) {
            res.put("check", "true");
            return res;
        }else{
            res.put("check","false");
            return res;
        }
    }
    @Override
    public JSONObject get_award(String stuId,String state){
        JSONObject result = new JSONObject();
        List<select_award> a =mapper.select_award(state);
        result.put("data",a);
        System.out.println(result);
        return result;
    }

    @Override
    public JSONObject update_award(String stuId,String state,String awardName,String time)
    {
        JSONObject result = new JSONObject();
        int a = mapper.update_award(stuId,state,awardName,time);
        result.put("check",a);
        System.out.println(result);
        return result;
    }
}
