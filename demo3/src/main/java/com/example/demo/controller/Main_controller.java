package com.example.demo.controller;

import com.example.demo.mapper.Student_mapper;
import com.example.demo.mapper.Teacher_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class Main_controller {
    @Autowired
    private Student_mapper mapper;

    @Autowired
    private Teacher_mapper teacher_mapper;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/Classroom")
    public String classroom(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
            model.addAttribute("selected_classroom",selected_classroom);
        }
        return "Classroom";
    }

    @RequestMapping("/Home")
    public String Home(Model model,String id,String type)  {
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
        }
        return "Home";
    }
    @RequestMapping("/course")
    public String Course(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
        }
        return "course";
    }

    @RequestMapping("/Project")
    public String project(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String  Project_Approval="/Project_Approval?id="+id+"&type="+type;
            String  project_process="/project_process?id="+id+"&type="+type;
            model.addAttribute("Project_Approval", Project_Approval);
            model.addAttribute("project_process", project_process);
        }
        return "Project";
    }

    @RequestMapping("/Project_Approval")
    public String Project_Approval(Model model,String id,String type){
        if (type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String  Project_Approval="/Project_Approval?id="+id+"&type="+type;
            String  project_process="/project_process?id="+id+"&type="+type;
            model.addAttribute("Project_Approval", Project_Approval);
            model.addAttribute("project_process", project_process);
        }
        return "Project_Approval";
    }

    @RequestMapping("/project_process")
    public String project_process(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String  Project_Approval="/Project_Approval?id="+id+"&type="+type;
            String  project_process="/project_process?id="+id+"&type="+type;
            model.addAttribute("Project_Approval", Project_Approval);
            model.addAttribute("project_process", project_process);
        }
        return "project_process";
    }

    @RequestMapping("/Information")
    public String information(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
        }
        return "Information";
    }

    @RequestMapping("/Award")
    public String Award(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
        }
        return "Award";
    }

    @RequestMapping("/select_course")
    public String Select(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String select_course="/select_course?id="+id+"&type="+type;
            model.addAttribute("select_course",select_course);
            String selected_course="/selected_course?id="+id+"&type="+type;
            model.addAttribute("selected_course",selected_course);
            String selectedList="/selectedList?id="+id+"&type="+type;
            model.addAttribute("selectedList",selectedList);

        }
        return "select_course";
    }

    @RequestMapping("/selected_course")
    public String selected_course(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String select_course="/select_course?id="+id+"&type="+type;
            model.addAttribute("select_course",select_course);
            String selected_course="/selected_course?id="+id+"&type="+type;
            model.addAttribute("selected_course",selected_course);
            String selectedList="/selectedList?id="+id+"&type="+type;
            model.addAttribute("selectedList",selectedList);
        }

        return "selected_course";
    }
    @RequestMapping("/selectedList")
    public String selectedList(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String select_course="/select_course?id="+id+"&type="+type;
            model.addAttribute("select_course",select_course);
            String selected_course="/selected_course?id="+id+"&type="+type;
            model.addAttribute("selected_course",selected_course);
            String selectedList="/selectedList?id="+id+"&type="+type;
            model.addAttribute("selectedList",selectedList);
        }
        return "selectedList";
    }
    @RequestMapping("/selected_classroom")
    public String selected_classroom(Model model,String id,String type){
        if(type.equals("student")){
            String name = mapper.findByStu(id);
            String classId=mapper.findclassId(id);
            inital_href(model,id,type,name,classId);
            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
            model.addAttribute("selected_classroom",selected_classroom);
        }
        return "selected_classroom";
    }

    @RequestMapping("/A_Home")
    public String AHome(Model model,String id,String type){

        return "A_Home";
    }
    @RequestMapping("/manager/student")
    public String student(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/student";
    }

    @RequestMapping("/manager/teacher")
    public String teacher(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/teacher";
    }
    @RequestMapping("/manager/manager")
    public String manager(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/manager";
    }

    @RequestMapping("/manager/show_course")
    public String showcourse(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/show_course";
    }
    @RequestMapping("/manager/jumpClass")
    public String jumpClass(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/jumpClass";
    }
    @RequestMapping("/manager/grade_list")
    public String grdelist(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/grade_list";
    }
    @RequestMapping("/manager/awardList")
    public String awardList(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/awardList";
    }
    @RequestMapping("/manager/project_list")
    public String projectlist(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        return "/manager/project_list";
    }
    @RequestMapping("/Teacher/Home")
    public String T_Home(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");

        return "/Teacher/Home";
    }
    @RequestMapping("/Teacher/Classroom")
    public String Teacher_Classroom(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");

        return "/Teacher/Classroom";
    }
    @RequestMapping("/Teacher/Project")
    public String Teacher_Project(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");

        return "/Teacher/Project";
    }
    @RequestMapping("/Teacher/SelectList")
    public String Teacher_SelectList(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");

        return "/Teacher/SelectList";
    }
    @RequestMapping("/Teacher/Course")
    public String Teacher_Course(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/Course";
    }
    @RequestMapping("/Teacher/CourseTable")
    public String Teacher_CourseTable(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/CourseTable";
    }

    @RequestMapping("/Teacher/HistoryCourse")
    public String Teacher_HistoryCourse(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "Teacher/HistoryCourse";
    }

    @RequestMapping("/Teacher/CourseMore")
    public String Teacher_CourseMore(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/CourseMore";
    }
    @RequestMapping("/Teacher/SelectCourse")
    public String Teacher_SelectCourse(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/SelectCourse";
    }
    @RequestMapping("/Teacher/CreateProject")
    public String Teacher_CreateProject(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/CreateProject";
    }
    @RequestMapping("/Teacher/SelectedCourse")
    public String Teacher_SelectedCourse(Model model,String id,String type){
//        if(type.equals("student")){
//            String name = mapper.findByStu(id);
//            inital_href(model,id,type,name);
//            String selected_classroom="/selected_classroom?id="+id+"&type="+type;
//            model.addAttribute("selected_classroom",selected_classroom);
//        }
        inital_T_href(model,id,type,"1");
        return "/Teacher/SelectedCourse";
    }

    @GetMapping("cha")
    public String cha(){
        return "login1";
    }

    @GetMapping("index")
    public String index(@RequestParam Map<String,Object> paramMap, Model model){
        model.addAttribute("username",paramMap.get("username"));
        return "chat";
    }

    public void inital_T_href(Model model,String id,String type,String name){
        String  t_Classroom="/Teacher/Classroom?id="+id+"&type="+type;
        String  t_home="/Teacher/Home?id="+id+"&type="+type;
        String  t_course="/Teacher/Course?id="+id+"&type="+type;
        String  t_Project="/Teacher/Project?id="+id+"&type="+type;
        String  t_HistoryCourse="/Teacher/HistoryCourse?id="+id+"&type="+type;
        String  t_SelectCourse="/Teacher/SelectCourse?id="+id+"&type="+type;
        String t_CourseTable="/Teacher/CourseTable?id="+id+"&type="+type;
        String t_CourseMore="/Teacher/CourseMore?id="+id+"&type="+type;
        String t_CreateProject="/Teacher/CreateProject?id="+id+"&type="+type;
        String t_SelectedCourse="/Teacher/SelectedCourse?id="+id+"&type="+type;
        String t_SelectList="/Teacher/SelectList?id="+id+"&type="+type;
        String a= teacher_mapper.teacherName(id);
        String t=id+" "+a;
       model.addAttribute("t", t);
        model.addAttribute("t_Classroom", t_Classroom);
        model.addAttribute("t_home",t_home);
        model.addAttribute("t_course",t_course);
        model.addAttribute("t_Project", t_Project);
        model.addAttribute("t_HistoryCourse", t_HistoryCourse);
        model.addAttribute("t_SelectCourse", t_SelectCourse);
        model.addAttribute("t_CourseTable", t_CourseTable);
        model.addAttribute("t_CreateProject", t_CreateProject);
        model.addAttribute("t_CourseMore", t_CourseMore);
        model.addAttribute("t_SelectedCourse", t_SelectedCourse);
        model.addAttribute("t_SelectList", t_SelectList);

    }
    public void inital_href(Model model,String id,String type,String name,String classId){
        String home ="/Home?id="+id+"&type="+type;
        String course ="/course?id="+id+"&type="+type;
        String  select_course="/select_course?id="+id+"&type="+type;
        String  Classroom="/Classroom?id="+id+"&type="+type;
        String  Project="/Project?id="+id+"&type="+type;
        String  Award="/Award?id="+id+"&type="+type;
        String Information="/Information?id="+id+"&type="+type;
        model.addAttribute("home",home);
        model.addAttribute("course",course);
        model.addAttribute("select_course", select_course);
        model.addAttribute("Classroom", Classroom);
        model.addAttribute("Project", Project);
        model.addAttribute("Award", Award);
        model.addAttribute("Information", Information);
        model.addAttribute("id", id+" "+name+" 班级:"+classId);
    }
}
