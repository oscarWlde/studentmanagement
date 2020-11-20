package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface Load_grade_service {
    JSONObject load_grade(String id, String type);

    JSONObject search_grade(String id, String year,String semster);

    JSONObject load_totalCredit(String id, String year);
}
