package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Student;
import com.example.demo.bean.Take;
import com.example.demo.mapper.Take_map;
import com.example.demo.service.MailService;
import com.example.demo.service.ManagerService;
import com.example.demo.service.award_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private award_service a_service;
    @Autowired
    private Take_map take_map;
    @RequestMapping("/manager/grade")
    public JSONObject managerGrade(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String str[] = jsonObject.getString("time").split("/");//grade代表年龄
        return managerService.managergrade(str[0],str[1],jsonObject.getString("academyName"),jsonObject.getString("grade"));
    }

    @RequestMapping("/manager/insertCourse")
    public JSONObject insertStu(@RequestBody String json){
        System.out.println(json);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = JSONObject.parseObject(json);
        String str[]=jsonObject.getString("courseOrder").split("-");
        System.out.println(str[0]+str[1]);
        Take temptake = new Take();
        temptake.setSemster(jsonObject.getString("semster"));
        temptake.setYear(jsonObject.getString("year"));
        temptake.setClassroomId(jsonObject.getString("classroomId"));
        temptake.setDay(jsonObject.getString("weekdataid"));
        temptake.setEndTime(jsonObject.getString("starttimeid"));
        temptake.setInformation(jsonObject.getString("information"));
        temptake.setStartTime(jsonObject.getString("starttimeid"));
        temptake.setTakeDate(df.format(new Date()));
        temptake.setTakeId(jsonObject.getString("courseOrder"));
        temptake.setType("1");
        temptake.setWeek(jsonObject.getString("time"));
        System.out.println(temptake.toString());
        take_map.addtake(temptake);

        return managerService.insertC(str[0],
               str[1],
                jsonObject.getString("courseOrder"),
                jsonObject.getString("courseName"),
                jsonObject.getString("capacity"),
               "0",
                jsonObject.getString("time"),
                jsonObject.getString("credit"),
                jsonObject.getString("semster"),
                jsonObject.getString("type"),
                jsonObject.getString("limitGrade"),
                jsonObject.getString("starttimeid"),
                jsonObject.getString("language"),
                jsonObject.getString("year"),
                jsonObject.getString("weekdataid"),
                null,
                jsonObject.getString("information")
        );
    }


    @RequestMapping("/manager/insertStudent")
    public JSONObject insert_student(@RequestBody String json)
    { System.out.println(json);

        JSONObject jsonObject = JSONObject.parseObject(json);
        Student student = new Student();
        String sex;
        System.out.println(jsonObject.getString("sex"));
        if(jsonObject.getString("sex").equals("男")){
            sex="m";
        }
        else {
            sex="f";
        }
        student.setBirthday(jsonObject.getString("birthday"));
        student.setClassId(jsonObject.getString("classId"));
        student.setComeTime(jsonObject.getString("stuId").substring(0,1));
        student.setStuName(jsonObject.getString("stuName"));
        student.setCreditGrade(Integer.parseInt(jsonObject.getString("totalCredit")));
        student.setFolk(jsonObject.getString("folk"));
        student.setNation(jsonObject.getString("nation"));
        student.setPassword(jsonObject.getString("password"));
        student.setSex(sex);
        student.setStuId(jsonObject.getString("stuId"));
        student.setTel(jsonObject.getString("tel"));
        student.setEmail(jsonObject.getString("email"));
        student.setAcademyName(jsonObject.getString("academy"));
        return managerService.insert_student(student);
    }
    @RequestMapping("/manager/changeClass")
    public JSONObject change_class(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return managerService.change_class(jsonObject.getString("stuId"),
                jsonObject.getString("classId"),
                jsonObject.getString("oldclassId"));
    }
    @RequestMapping("/manager/GRADE")
    public JSONObject grade(@RequestBody String json){

        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.getString("year"));
        if(jsonObject.getString("year").equals("all")){

            System.out.println(  managerService.grade(jsonObject.getString("academyName"),jsonObject.getString("grade"),jsonObject.getString("semster")));
            return managerService.grade(jsonObject.getString("academyName"),jsonObject.getString("grade"),jsonObject.getString("semster"));
        }
        else {
            System.out.println( managerService.certain_year_gpa(jsonObject.getString("academyName"),jsonObject.getString("grade"),jsonObject.getString("year")));
            //按年份查 year acedemyName grade
            return managerService.certain_year_gpa(jsonObject.getString("academyName"),jsonObject.getString("grade"),jsonObject.getString("year"));
        }

    }
    @RequestMapping("/manager/projectlist")
    public JSONArray managerlist(@RequestBody String json){
      JSONObject jsonObject = JSONObject.parseObject(json);
      System.out.println(jsonObject.getString("state"));
    System.out.println(managerService.managerall_the_project(jsonObject.getString("state")));
        return managerService.managerall_the_project(jsonObject.getString("state"));
    }

    @RequestMapping("/manager/load/course")
    public JSONObject managerloadcourse(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return managerService.managerloadCourse(jsonObject.getString("semester"),jsonObject.getString("year"),jsonObject.getString("academyName"),jsonObject.getString("Nianji"));
    }

    @RequestMapping("/manager/delete")
    public JSONObject managerdelete(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return managerService.deleteC(jsonObject.getString("semster"),jsonObject.getString("year"),jsonObject.getString("courseOrder"),jsonObject.getString("courseId"),jsonObject.getString("takeId"));
    }

    @RequestMapping("/manager/lower")
    public JSONObject Warning(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        return managerService.warning(jsonObject.getString("year"));
    }

    @RequestMapping("/manager/updateAward")
    public JSONObject update_award(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println("-----------------");
        System.out.println(json);
        return a_service.update_award(jsonObject.getString("stuId"),jsonObject.getString("state"),jsonObject.getString("awardName"),jsonObject.getString("time"));

    }

}
