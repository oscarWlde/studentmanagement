package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Student;
import com.example.demo.util.teacher.return_today_course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Student_mapper {
//
//    @Select("select * from Student")
//    List<Student> get_student();

    @Select("select * from Student where stuId=#{stuId} and password = #{password}")
    Student findByStuid(@Param("stuId") String stuId, @Param("password") String password);

    @Select("select stuName from Student where stuId=#{stuId}")
    String findByStu(String stuId);

    @Select("select classId from Student where stuId = #{stuId}")
    String findclassId(String stuId);

    @Select("select course.courseNum,course.courseName,time,classroomId from course\n" +
            "left join sc on sc.courseNum = course.courseNum\n" +
            "left join student on student.stuId = sc.stuId\n" +
            "left join take on take.takeId = sc.stuId\n" +
            "where student.stuId = #{stuId} and time = #{weekdata};")
    List<return_today_course> load_stu_course(String id, String weekdata);

    @Select("select * from student where stuId = #{stuId}")
    List<Student> loadinfo(String stuId);
}
