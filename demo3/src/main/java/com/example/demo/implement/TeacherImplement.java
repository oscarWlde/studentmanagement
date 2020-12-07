package com.example.demo.implement;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Course;
import com.example.demo.mapper.Teacher_mapper;
import com.example.demo.service.Teacher_service;
import com.example.demo.util.classroom.teacher_room;
import com.example.demo.util.student.return_select_course;
import com.example.demo.util.teacher.return_today_course;
import com.example.demo.util.teachergrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherImplement implements Teacher_service {
    @Autowired
    Teacher_mapper mapper;

    @Override//加载今天的课程
    public JSONObject load_today(String id, String weekdata) {
        JSONObject res = new JSONObject();
        List<return_today_course> today_courses = mapper.load_today(id, weekdata);
        if (today_courses!=null){
            res.put("data",today_courses);
            return res;
        }
        else{
             res.put("check",false);
             return res;
        }
    }

    @Override//返回老师将要选的课程
    public JSONObject select_my_data1(String year, String semster) {
        JSONObject res = new JSONObject();
        List<Course> course = mapper.select_my_data(year, semster);
        if(course!=null){
            res.put("data",course);
            res.put("check",1);
            return res;
        }
        else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONObject returnormal(String teacherId, String year, String semester, String courseNum) {
        List<teachergrade>  teachergrades = mapper.returnormal(teacherId,year,semester,courseNum);
        JSONObject res = new JSONObject();
        res.put("data",teachergrades);
        return res;
    }

    @Override
    public JSONObject updategrade(String stuId, String courseNum, String year, String semester,String pgrade,String grade,String totalgrade) {
        JSONObject res = new JSONObject();
        res.put("message","update");
        System.out.println(pgrade);
        System.out.println(grade);
        System.out.println(totalgrade);
        int a =  mapper.updatepgrade(pgrade,stuId,courseNum,semester,year);

        int b = mapper.updategrade( grade,stuId,courseNum,semester ,year);
        int c =  mapper.updatetotalgrade(stuId,courseNum,year,semester,totalgrade);
        System.out.println(a+""+b+""+c);
        return res;
    }

    @Override
    public JSONObject insert_selected_course(String id,
                                             String courseNum,
                                             String courseName,
                                             String courseId,
                                             String courseOrder,
                                             String type,
                                             String weekdata,
                                             String time,
                                             String courseMemberNum,
                                             String semster,
                                             String year) {

        String teacherName = mapper.teacherName(id);
        System.out.println(teacherName);
        mapper.insert_selected_course(teacherName,
                                        courseName,
                                        courseNum,
                                        courseId,
                                        courseOrder,
                                        type,
                                        weekdata,
                                        time,
                                        courseMemberNum,
                                        semster,
                                        year);
        return null;
    }

    @Override
    public JSONObject select_history_data(String id, String year) {
        JSONObject res = new JSONObject();
        List<return_select_course> return_teacher_courses = mapper.select_history_data(id, year);
        if(return_teacher_courses!=null){
            res.put("data",return_teacher_courses);
            return res;
        }
        else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONObject select_my_data2(String teacherId, String year, String semester) {
        JSONObject res = new JSONObject();
        List<Course> course = mapper.selectdata2(teacherId, year, semester);
        if(course!=null){
            res.put("data2",course);
            res.put("check",2);
            return res;
        }
        else{
            res.put("check",false);
            return res;
        }
    }

    @Override//修改某门课的教师
    public JSONObject upp(String courseOrder, String courseId, String semster, String year,String teacherId) {
        JSONObject res = new JSONObject();
       mapper.upp(courseOrder,courseId,semster,year,teacherId);
       res.put("check","update");
       System.out.println("the teacherid is :" + teacherId);
       return res;
    }

    @Override
    public JSONObject Selected_courses(String tId,String semester,String year){
        JSONObject res = new JSONObject();
        List<teacher_room> a = mapper.selected_course(tId,semester,year);
        res.put("data",a);
        return res;
    }


}
