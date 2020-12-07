package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Awardpunishment;
import com.example.demo.mapper.Award_mapper;
import com.example.demo.service.award_service;
import com.example.demo.util.award.return_award;
import com.example.demo.util.award.select_award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Gataward;

import java.util.List;

@Service
public class AwardImplement implements award_service {
    @Autowired
    private Award_mapper mapper;

    /**
     * 获得学号为id的award信息
     * @param id
     * @return 奖学金信息
     */
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

    /**
     * 获得所有award信息
     * @return
     */
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

    /**
     *将新的奖学金信息插入
     * @param stuId
     * @param tag
     * @param awardName
     * @param state
     * @param descript
     * @param time
     * @return
     */
    @Override
    public JSONObject insert_awrad(String stuId,String tag,String awardName,String state,String descript,String time){
        JSONObject res = new JSONObject();
        List<Gataward> a = mapper.find(stuId,awardName);
        if(a.size() == 0) {
            mapper.insert_award(stuId,awardName,tag,state,descript,time);
            res.put("check", "true");
            return res;
        }else{
            res.put("check","false");
            return res;
        }
    }

    /**
     * 获得某个阶段的所有奖学金的信息
     * @param stuId
     * @param state
     * @return
     */
    @Override
    public JSONObject get_award(String stuId,String state){
        JSONObject result = new JSONObject();
        List<select_award> a =mapper.select_award(state);
        result.put("data",a);
        System.out.println(result);
        return result;
    }

    /**
     * 更新奖学金状态
     * @param stuId
     * @param state
     * @param awardName
     * @param time
     * @return
     */
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
