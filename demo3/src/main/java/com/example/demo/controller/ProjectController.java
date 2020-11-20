package com.example.demo.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.Project_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    @Autowired
    Project_service service;

    @RequestMapping("/teacher/create/project")//可以的（成功）
    public JSONObject create_project(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.create_project(jsonObject.getString("projectNum"),
                                        jsonObject.getString("projectName"),
                                         jsonObject.getString("projectType"),
                                            jsonObject.getString("teacherNum"),
                                                 jsonObject.getInteger("studentNum"),
                                                    jsonObject.getString("startTime"),
                                                        jsonObject.getString("endTime"));
    }

    @RequestMapping("/student/personalProject")//查询某个人项目（成功）
    public JSONObject personal_project(@RequestBody String json){

        JSONObject jsonObject = JSONObject.parseObject(json);
        return service.personal_project(jsonObject.getString("stuId"));
    }

    @RequestMapping("/teacher/allTheProject")//查询全部项目
    public JSONArray all_the_project(@RequestBody String json){

       JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(service.all_the_project(jsonObject.getString("teacherId"),jsonObject.getString("state")));
        return service.all_the_project(jsonObject.getString("teacherId"),jsonObject.getString("state"));
    }

    @RequestMapping("/teacher/projectapply")//教师审核project
    public JSONObject projectapply(@RequestBody String json){
        JSONArray array=JSONArray.parseArray(json);
        JSONObject res = new JSONObject();
        res.put("data","ok");
        for(int i=0;i<array.size();i++){
            service.updateproject(array.getJSONObject(i).getString("projectId"));
        }
        return res;
    }

    @RequestMapping("/student/allTheProject")
    public JSONObject allstuproject(){
        System.out.println(service.allStuproject());
        return service.allStuproject();
    }

    @RequestMapping("/student/search_project")
    public JSONArray allsearchstuproject(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(service.stusearch(jsonObject.getString("Id"),jsonObject.getString("state")));
        return service.stusearch(jsonObject.getString("Id"),jsonObject.getString("state"));

    }

    @RequestMapping("/student/insertP")
    public void insertP(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String str[] = jsonObject.getString("stuId").split("/");
        for(int i=0;i<str.length;i++) {
            service.insertPro(jsonObject.getString("projectId"), str[i], "0", "0", jsonObject.getString("applyTime"));
        }

    }

    @RequestMapping("/teacher/judgetheproject")
    public JSONObject judgetheproject(@RequestBody String json){
        JSONObject res= new JSONObject();
        JSONObject jsonObject = JSONObject.parseObject(json);
        res.put("data","inserted");
        String str[] = jsonObject.getString("other").split("/");
        System.out.println(jsonObject.getString("state"));
        for(int i=0;i<str.length;i++){
        service.judgeppp(jsonObject.getString("projectId"),str[i],jsonObject.getString("state"),jsonObject.getString("mylevel"),jsonObject.getString("applyTime"));
        }
        return  res;
    }

    @RequestMapping("/student/ssssssearchacademy")
    public JSONObject sss(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        if(jsonObject.getString("academyName").equals("0")){
            System.out.println(service.allsss());
            return service.allsss();
        }
        else {
        return service.sssearchacademy(jsonObject.getString("academyName"));
        }
    }




    @RequestMapping("/student/projecttest")
    public JSONObject search_student()
    {

        return service.search_student("2019001");
    }



}
