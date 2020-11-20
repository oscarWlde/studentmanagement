package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Course;
import com.example.demo.bean.Teacher;
import com.example.demo.util.classroom.teacher_room;
import com.example.demo.util.student.return_select_course;
import com.example.demo.util.teacher.return_today_course;
import com.example.demo.util.teachergrade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Teacher_mapper {

    @Select("select * from teacher where tId = #{tId} and mypassword = #{mypassword}")
    Teacher findbyTeacher(@Param("tId") String tId, @Param("mypassword") String mypassword);

    @Select("select courseNum,course.courseName,time,classroomId from course\n" +
            "left join teacher on teacher.teacherName = course.teacherName\n" +
            "left join take on take.takeId = teacher.tId\n" +
            "where teacher.tId = #{id} and time = #{weekdata};")
    List<return_today_course> load_today(String id, String weekdata);

    @Select("select * from course where teacherId is null and year = #{year} and semster =#{semster}")
    List<Course> select_my_data(@Param("year") String year, @Param("semster") String semster);

    @Insert("insert into course(teacherName,courseNum,courseName,courseId,courseOrder,type,weekdata,time,courseMemberNum,semster,year) " +
            "values('#{teacherName}','#{courseNum}','#{courseName}','#{courseId}','#{courseOrder}','#{type}','#{weekdata}','#{time}','#{courseMemberNum}','#{semster}','#{year}');")
    int insert_selected_course(String teacherName,
                               String courseNum,
                               String courseName,
                               String courseId,
                               String courseOrder,
                               String type,
                               String weekdata,
                               String time,
                               String courseMemberNum,
                               String semster, String year);

    @Select("select teacherName from teacher where tId = #{id}")
    String teacherName(String id);

    @Select("select courseNum,courseName,academyName,type,time,weekdata from course\n" +
            "left join teacher on teacher.teacherName = course.courseName\n" +
            "where tId = #{id} and year != #{year};")
    List<return_select_course> select_history_data(String id, String year);

    @Select("select mypassword from teacher where tId = #{tId}")
    String  findbyTea(String tId);

    @Select("select * from course where year = #{year} and semster = #{semster} and teacherId = #{teacherId}")
    List<Course> selectdata2(@Param(("teacherId")) String teacherId, @Param("year") String year, @Param("semster") String semster);

    @Update("update course set teacherId = #{teacherId} where courseOrder = #{courseOrder} and courseId = #{courseId} and semster = #{semster} and year = #{year};")
    void upp(@Param("courseOrder") String courseOrder, @Param("courseId") String courseId, @Param("semster") String semester, @Param("year") String year,@Param("teacherId") String teacherId);

    @Select("select course.courseNum,courseName,take.startTime,take.`day`,take.classroomId\n" +
            "from course,take\n" +
            "where course.teacherId=#{tId} and course.courseNum=take.takeId and take.`year`=#{year} and take.semester=#{semester} ")
    List<teacher_room> selected_course(@Param("tId")String tId,@Param("semester")String semester,@Param("year")String year);

    @Select("select distinct student.stuId,stuName,pgrade,sc.grade,totalgrade from sc\n" +
            "left join student on student.stuId = sc.stuId\n" +
            "left join course on course.courseNum = sc.courseNum\n" +
            "left join teacher on teacher.tId = course.teacherId \n" +
            "where tId = #{teacherId} and myyear = #{year} and sc.semster = #{semester} and sc.courseNum = #{courseNum}")
    List<teachergrade> returnormal(@Param("teacherId") String teacherId, @Param("year") String year, @Param("semester") String semester, @Param("courseNum") String courseNum);

    @Update("update sc set pgrade = #{pgrade} where stuId = #{stuId} and courseNum = #{courseNum} and semster = #{semester} and myyear = #{myyear};")
    int updatepgrade(@Param("pgrade") String pgrade,@Param("stuId") String stuId,@Param("courseNum") String courseNum,@Param("semester") String semester ,@Param("myyear") String myyear);

    @Update("update sc set grade = #{grade} where stuId = #{stuId} and courseNum = #{courseNum} and semster = #{semester} and myyear = #{myyear};")
    int updategrade(@Param("grade") String grade,@Param("stuId") String stuId,@Param("courseNum") String courseNum,@Param("semester") String semester ,@Param("myyear") String myyear);


    @Update("update sc set totalgrade = #{totalgrade} where stuId = #{stuId} and courseNum = #{courseNum} and semster = #{semester} and myyear = #{year};")
    int updatetotalgrade(@Param("stuId")String stuId, @Param("courseNum")String courseNum,@Param("year") String year,@Param("semester") String semester ,@Param("totalgrade") String totalgrade);

    @Select("select teacherName from teacher where tId = #{teacherId};")
    String loadallingo(String teacherId);
}
