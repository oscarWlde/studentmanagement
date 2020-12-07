package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Pickproject;
import com.example.demo.bean.Project;
import com.example.demo.bean.sproject;
import com.example.demo.util.project.returnStuProject;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ListIterator;

public interface Project_map {
    @Select("select * from Project")
    List<Project> get_project();

    @Delete("delete from Project where project_id=#{projectId}")
    int delete_project(Project p);

    @Insert("insert into Project values(#{projectId},#{projectName},#{projectType},#{studentNum},#{teacherId},#{startTime},#{endTime})")
    int insert_project(Project p);

    @Update("update Project set project_type = #{projectType} where project_id=#{projetId}")
    int update_project(Project p);

    @Update("update Project set project_time = #{projectTime} where project_id=#{projetId}")
    int update_time(Project p);

    @Update("update Project set student_num = #{studentNum} where project_id=#{projetId}")
    int update_num(Project p);

    @Update("update Project set project_name = #{ProjectName} where project_id=#{projetId}")
    int update_name(Project p);

    @Update("update Project set teacher_id = #{teacherId} where project_id=#{projetId}")
    int upate_teacher(Project p);

    @Select("select * from studentproject where stuId = #{stuId}")
    List<Pickproject> personal_project(@Param("stuId") String stuId);

    @Select("select distinct project.projectId from studentproject \n" +
            "left join project on project.projectId = studentproject.projectId\n" +
            "where state =#{state} and teacherId = #{teacherId} ;")
    String[] all_the_project(String teacherId,String state);

    @Select("select distinct project.projectId,project.projectName,project.projectType,teacher.teacherName,stuId,state,mylevel,applytime from studentproject \n" +
            "left join project on project.projectId = studentproject.projectId\n" +
            "LEFT JOIN teacher on teacher.tId = project.teacherId\n" +
            "where  project.projectId = #{projectId} and studentproject.state = #{state}")
    List<Pickproject> returnppp( String projectId,String state);

    @Update("update studentproject set state = '1' where projectId=#{projectId}")
    int update_projectState(@Param("projectId") String projectId);


   @Select("select distinct project.projectId,projectName,projectType,teacherName,academyName,studentNum from project  \n" +
           "left join studentproject on project.projectId = studentproject.projectId\n" +
           "left join teacher on teacher.tId = project.teacherId;")
    List<returnStuProject> allstuProject();

    @Select("select projectId from studentproject where stuId = #{stuId}")
    ListIterator<String> searchProject(String stuId);

    @Select("select project.projectId,project.stuId,projectName,projectType,teacherName,academyName,studentNum from studentproject \\n\" +\n" +
            "           \"left join project on project.projectId = studentproject.projectId\\n\" +\n" +
            "           \"left join teacher on teacher.tId = project.teacherId where projectId = #{projectId};")
    List<returnStuProject> searchallinfor(String projectId);

    @Insert("insert into studentproject values(#{projectId},#{stuId},#{state},#{mylevel},#{applytime})")
    void insertStuPro(Pickproject project);


    @Select("select stuName,projectId from student,studentproject \n" +
            "where student.stuId in \n" +
            "(select studentproject.stuId from studentproject\n" +
            "where projectId in\n" +
            "(select projectId from studentproject\n" +
            "where studentproject.stuId = #{stuId1}))\n" +
            "and student.stuId !=#{stuId2}\n" +
            "and studentproject.stuId = student.stuId\n" +
            "and projectId in (select projectId from studentproject\n" +
            "where studentproject.stuId = #{stuId3})")
    List<sproject> search_student(@Param("stuId1")String stuId1, @Param("stuId2")String stuId2, @Param("stuId3")String stuId3);

    @Select("select distinct project.projectId from studentproject \n" +
            "left join project on project.projectId = studentproject.projectId\n" +
            "where state = #{state} and studentproject.stuId = #{stuId};")
    String[] stusearch(String stuId,String state);

    @Select("select distinct stuId,project.projectId,project.projectName,project.projectType,teacher.teacherName,state,mylevel,applytime from studentproject \n" +
            "left join project on project.projectId = studentproject.projectId\n" +
            "LEFT JOIN teacher on teacher.tId = project.teacherId\n" +
            "where state =#{state} and project.projectId = #{stuId}")
    List<Pickproject> returnsss(String stuId,String state);

    @Insert("insert into studentproject values(#{projectId},#{stuId},#{state},#{mylevel},#{applytime})")
    void judgeppp(Pickproject pickproject);

    @Select("select distinct project.projectId from studentproject\n" +
            "left join project on project.projectId = studentproject.projectId\n" +
            "where state = #{state};")
    String[] managerall_the_project(String state);

    @Select("select distinct project.projectId,projectName,projectType,teacherName,academyName,studentNum from project \n" +
            "left join studentproject on project.projectId = studentproject.projectId\n" +
            "left join teacher on teacher.tId = project.teacherId\n" +
            "where academyName = #{academyName};")
    List<returnStuProject> sssearchaca(String academyName);

    @Select("select distinct project.projectId,projectName,projectType,teacherName,academyName,studentNum from project \n" +
            "left join studentproject on project.projectId = studentproject.projectId\n" +
            "left join teacher on teacher.tId = project.teacherId")
    List<returnStuProject> sss();
}

