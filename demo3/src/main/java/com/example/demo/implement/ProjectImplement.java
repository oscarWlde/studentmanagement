package com.example.demo.implement;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Pickproject;
import com.example.demo.bean.Project;
import com.example.demo.bean.sproject;
import com.example.demo.mapper.Project_map;
import com.example.demo.service.Project_service;
import com.example.demo.util.project.returnStuProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectImplement implements Project_service {
    @Autowired
    Project_map project_map;

    @Override
    public JSONObject create_project(String projectId,String projectName,String type,String teacherId,long studentNum,String startTime,String endTime)
    {
        JSONObject result = new JSONObject();
        Project p = new Project();
        p.setProjectId(projectId);
        p.setProjectName(projectName);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
        String today = dateFormat.format( now );
        p.setStartTime(startTime);
        p.setEndTime(endTime);
        p.setProjectType(type);
        p.setStudentNum(studentNum);
        p.setTeacherId(teacherId);
        project_map.insert_project(p);
        result.put("check","true");
        return result;
    }

    @Override
    public JSONObject personal_project(String StuId)
    {
        JSONObject result = new JSONObject();
        List<Pickproject> a = project_map.personal_project(StuId);
        result.put("data",a);
        return result;
    }

    @Override
    public JSONArray all_the_project(String teacherId,String state)
    {
        String[] a;
        a = project_map.all_the_project(teacherId,state);
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
    public void updateproject(String projectId) {

       project_map.update_projectState(projectId);


    }

    @Override
    public JSONObject allStuproject() {
        List<returnStuProject> returnStuProjects = project_map.allstuProject();
        JSONObject res = new JSONObject();
        if(returnStuProjects!=null){
            res.put("data",returnStuProjects);
            return res;
        }else{
            res.put("check",false);
            return res;
        }
    }

    @Override
    public JSONArray stusearch(String stuId, String state) {

        String[] a = project_map.stusearch(stuId,state);
        JSONArray result = new JSONArray();

        for(int i = 0;i<a.length;i++){
            List<Pickproject> pickprojects = project_map.returnsss(a[i],state);
            JSONObject result1 = new  JSONObject();
            result1.put("inf",pickprojects);
            result.add(result1);
        }
        return result;
    }

    @Override
    public void insertPro(String projectId, String stuId, String state, String mylevel, String applytime) {
        Pickproject project = new Pickproject();
        project.setProjectId(projectId);
        project.setStuId(stuId);
        project.setState(state);
        project.setMylevel(mylevel);
        project.setApplytime(applytime);
        project_map.insertStuPro(project);
    }

    @Override
    public JSONObject search_student(String stuId) {
        JSONObject result = new JSONObject();
        List<sproject> a = project_map.search_student(stuId,stuId,stuId);
        result.put("data",a);
        return result;
    }

    @Override
    public void judgeppp(String projectId, String stuId, String state, String mylevel, String applytime) {
        Pickproject pickproject = new Pickproject();
        pickproject.setProjectId(projectId);
        pickproject.setStuId(stuId);
        pickproject.setMylevel(mylevel);
        pickproject.setApplytime(applytime);
        pickproject.setState(state);
        project_map.judgeppp(pickproject);

    }

    @Override
    public JSONObject sssearchacademy(String academyName) {
        JSONObject res = new JSONObject();
        System.out.println(academyName);
        List<returnStuProject> returnStuProjects = project_map.sssearchaca(academyName);
        res.put("data",returnStuProjects);
        return res;


    }

    @Override
    public JSONObject allsss() {
        JSONObject res = new JSONObject();
        List<returnStuProject> returnStuProjects = project_map.sss();
        res.put("data",returnStuProjects);
        return res;

    }


}
