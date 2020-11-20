package com.example.demo.implement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Course;
import com.example.demo.bean.Pickproject;
import com.example.demo.bean.Student;
import com.example.demo.mapper.ManagerMapper;
import com.example.demo.mapper.Project_map;
import com.example.demo.service.ManagerService;
import com.example.demo.util.manager.returnAcademyGrade;
import com.example.demo.util.manager.returnGrade;
import com.example.demo.util.manager.year_gpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerImplement implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private Project_map project_map;
    @Override
    public JSONObject managergrade(String year, String semster, String academyName, String grade) {
        JSONObject res = new JSONObject();
        List<returnGrade> returnGrades = managerMapper.getGrade(year,semster,academyName,grade);
        if(returnGrades!=null){
            res.put("data",returnGrades);
            return res;
        }else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONObject insertC(String courseId, String courseOrder, String courseNum, String courseName, String courseMemberNum, String capacity, String courseTotalTimes, String credit, String semster, String type, String limitGrade, String time, String language, String year, String weekdata, String teacherName, String information) {
        JSONObject res = new JSONObject();
        System.out.println(courseId+courseOrder+ courseNum+courseName+courseMemberNum + capacity+ courseTotalTimes+ credit+ semster+type+ limitGrade+  time+ language+ year+weekdata+ teacherName+information) ;

         int cmn = Integer.parseInt(courseMemberNum);
          int  ctt = Integer.parseInt(courseTotalTimes);
         int cd = Integer.parseInt(credit);
        System.out.println(cmn+ctt+cd);
        String teacherId = null;
        Course course = new Course(courseId,courseOrder, courseNum, courseName,  cmn,  capacity, ctt, cd,  semster,  type,  limitGrade,  time, language, year,  weekdata,  teacherName,  information,teacherId);
        System.out.println(course.toString());

        managerMapper.insertC(course);
        res.put("check","inserted");
        return res;
    }


    @Override
    public  JSONObject insert_student(Student student)
    {
        JSONObject res = new JSONObject();
        managerMapper.insertStudent(student);
        res.put("check","inserted");
        return res;
    }


    @Override
    public JSONObject change_class(String stuId,String classId,String oldclassId)
    {
        JSONObject res = new JSONObject();
        managerMapper.change_class(classId,stuId,oldclassId);
        res.put("check","inserted");
        return res;
    }

    @Override
    public JSONObject grade(String academyName,String year,String semster) {
        JSONObject res = new JSONObject();
        List<returnAcademyGrade> returnAcademyGrades = managerMapper.getacademyGrade(academyName,year,semster);
        res.put("data",returnAcademyGrades);
        return res;
    }

    @Override
    public JSONArray managerall_the_project(String state) {
        String[] a;
        a = project_map.managerall_the_project(state);
        JSONArray result = new JSONArray();
        for(int i = 0;i<a.length;i++){
            List<Pickproject> pickprojects = project_map.returnppp(a[i],state);
            JSONObject result1 = new  JSONObject();
            result1.put("inf",pickprojects);
            result.add(result1);
        }
        return result;
    }
    @Override
    public JSONObject certain_year_gpa(String academy,String grade,String year){
        JSONObject result = new JSONObject();
        List<returnAcademyGrade> year_gpas = managerMapper.get_certain_year(year,grade,academy);
        result.put("data",year_gpas);
        return result;
    }

    @Override
    public JSONObject managerloadCourse(String semster, String year, String academyName, String grade) {
        JSONObject res = new JSONObject();
        List<Course> courses = managerMapper.serlectloadingCourse(semster,year,academyName,grade);
        res.put("data",courses);
        return res;

    }

    @Override
    public JSONObject deleteC(String semster, String year, String courseOrder, String courseId,String takeId) {
        JSONObject res = new JSONObject();
        managerMapper.deleteclassroom(takeId);
        managerMapper.deletecourse(courseId,courseOrder,year,semster);
        res.put("data","delete");
        return res;
    }

    @Override
    public JSONObject warning(String year){
        JSONObject result = new JSONObject();
        List<returnAcademyGrade> a = managerMapper.waring(year);
        result.put("data",a);
        return result;

    }

}
