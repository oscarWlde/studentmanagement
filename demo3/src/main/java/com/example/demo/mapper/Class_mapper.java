package com.example.demo.mapper;

import com.example.demo.bean.Sc;
import com.example.demo.util.student.return_date;
import com.example.demo.util.student.return_student_course;
import com.example.demo.util.student.return_select_course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Class_mapper {

    @Select("select time,year from course order by year desc;")//提取time,year
    List<return_date> gettime();

    @Select("select courseName,teacher.teacherName,course.`year`,semster,weekdata,time,buildingName,classroom.classroomId,startTime,endTime from course \n" +
            "left join teacher on teacher.teacherName = course.teacherName\n" +
            "left join applyforclassroom on applyforclassroom.takeId = teacher.tId\n" +
            "left join classroom on classroom.classroomId = applyforclassroom.classroomId")//提取全部课程
    List<return_student_course> getmyClass();

    @Select("select courseName,teacher.teacherName,course.`year`,semster,weekdata,time,buildingName,classroom.classroomId,startTime,endTime from course \n" +
            "left join teacher on teacher.teacherName = course.teacherName\n" +
            "left join applyforclassroom on applyforclassroom.takeId = teacher.tId\n" +
            "left join classroom on classroom.classroomId = applyforclassroom.classroomId\n" +
            "where course.year = #{year} and semster = #{semster};")//提取指定的全部课程
    List<return_student_course> getregularClass(String year, String semster);

    @Select("select courseNum,courseName,teacher.teacherName,teacher.academyName,type,time,courseMemberNum,capacity,information,limitGrade from course \n" +
            "left join teacher on teacher.teacherName = course.teacherName\n" +
            "left join academy on academy.academyName = teacher.academyName where limitGrade = #{limitgrade} and course.type = #{type} and teacher.academyName = #{academyName};\n")
    List<return_select_course> get_select_course(String limitgrade,String type,String academyName);

    @Select("select grade from student where stuId = #{id}")
    String return_limit_grade(String id);

    @Select("select course.courseNum,courseName,teacher.teacherName,teacher.academyName,type,time,capacity,courseMemberNum,information from course \n" +
            "left join teacher on teacher.teacherName = course.teacherName\n" +
            "left join academy on academy.academyName = teacher.academyName \n" +
            "left join sc on sc.courseNum = course.courseNum\n" +
            "where stuId =#{id} and state = #{state} and type=#{type};")
    List<return_select_course> get_selected_course(String id,String state,String type,String academyName);

    @Select("insert into sc values(#{stuId}, #{courseNum}, #{myyear}, #{semster},#{state},#{pgrade},#{grade},#{totalgrade})")
    void refresh_inserted_course(Sc sc);

    @Delete("delete from sc where stuId = #{stuId} and courseNum = #{courseNum} and myyear=#{myyear} and semster = #{semster}")
    void deletesc(String stuId,String courseNum, String myyear, String semster);
}
