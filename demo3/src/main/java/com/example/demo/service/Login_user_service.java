package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface Login_user_service {

    JSONObject log_my_stu(String username, String password, String person);

    JSONObject log_my_tea(String username, String password, String person);

    JSONObject log_my_manager(String username, String password, String person);

    JSONObject loadinfo(String stuId);

    JSONObject email(String stuId,String newpassword,String email);
}
