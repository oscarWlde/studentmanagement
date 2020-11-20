package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface award_service {
    JSONObject load_award(String id);

    JSONObject load_all_award();

    JSONObject insert_awrad(String stuId,String tag,String awardName,String state,String descript,String time);

    JSONObject get_award(String stuId,String state);

    JSONObject update_award(String stuId,String state,String awardName,String time);
}
