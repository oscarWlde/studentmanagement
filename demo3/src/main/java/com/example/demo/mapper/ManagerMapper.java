package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Course;
import com.example.demo.bean.Student;
import com.example.demo.util.manager.returnAcademyGrade;
import com.example.demo.util.manager.returnGrade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ManagerMapper {
    @Select("select stuId,stuName,grade,gpa from student order by gpa des where academyName = #{academyName} and limitGrade = #{grade}")
    List<returnGrade> getGrade(String year, String semster, String academyName, String grade);

    @Insert("insert into course values(#{courseId},#{courseOrder},#{courseNum},#{courseName},#{courseMemberNum},#{capacity},#{credit},#{courseTotalTimes},#{semster},#{type},#{limitGrade},#{time},#{language},#{year},#{weekdata},#{teacherName},#{information},#{teacherId})")
    int insertC(Course course);

    @Insert("insert into student values(#{stuId},#{stuName},#{password},#{sex},#{totalCredit},#{birthday},#{email},#{classId},#{folk},#{nation},#{creditGrade},#{comeTime},#{grade},#{gpa},#{tel},#{academyName})")
    int insertStudent(Student student);

    @Update("update student set classId = #{classId} where stuId = #{stuId} and classId=#{oldclassId}")
    int change_class(@Param("classId") String classId, @Param("stuId") String stuId, @Param("oldclassId") String oldclassId);

    @Select("select stuId,stuName,gpa,academyName,grade from student where academyName = #{academy} and grade=#{grade} order by gpa DESC;")
    List<returnAcademyGrade> getacademyGrade(@Param("academy") String academy,@Param("grade") String grade,String semster);

    @Select("call year_gpa(#{year},#{grade},#{academyName})")
    List<returnAcademyGrade> get_certain_year(@Param("year")String year,@Param("grade")String grade,@Param("academyName")String academyName);

    @Select("select distinct * from Course \n" +
            "left join teacher on teacher.teacherName = Course.teacherName\n" +
            "left join take on take.takeId = Course.courseNum\n" +
            "where semster = #{semster} and Course.year = #{year}  and limitGrade = #{grade} ")
    List<Course> serlectloadingCourse(String semster, String year, String academyName, String grade);

    @Delete("delete from take where takeId = #{takeId}")
    void deleteclassroom(@Param("takeId") String takeId);

    @Delete("delete from course where courseId = #{courseId} and courseNum = #{courseOrder} and year = #{year} and semster = #{semster} ")
    void deletecourse(@Param("courseId") String courseId,@Param("courseOrder") String courseOrder,@Param("year") String year,@Param("semster") String semster);

    @Select("call academic_warning(#{myyear})")
    List<returnAcademyGrade> waring(@Param("myyear")String year);
}