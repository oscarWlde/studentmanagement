package com.example.demo.mapper;

import com.example.demo.bean.Course;
import com.example.demo.bean.Sc;
import com.example.demo.util.sc.course_return;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SC_map {

    @Select("select * from Sc")
    List<Sc> get_Sc();

    @Select("select * from course where courseNum in (select courseNum from sc where\n" +
            "stuId = #{id} and sc.myyear = #{myyear} and sc.semster = #{semester})  \n")
    List<Course> load_selected_courseTable(@Param("id") String id, @Param("type") String type, @Param("myyear") String year,@Param("semester")String semester);

    @Select("select course.courseNum,course.courseName,teacher.teacherName,applyforclassroom.classroomId,course.time,course.weekdata,student.gpa\n" +
            "            from course,applyforclassroom,student,teacher\n" +
            "            where student.stuId=#{id}\n" +
            "            and course.courseNum in (select sc.courseNum\n" +
            "            from sc where stuId =#{id} )\n" +
            "            and course.courseNum=applyforclassroom.takeId\n" +
            "            and course.teacherId=teacher.tId\n" +
            "            and course.semster =#{semester}\n" +
            "            and course.`year`=#{myyear}")
    List<course_return> ultimate_coursetable(@Param("id") String id, @Param("type") String type, @Param("myyear") String year, @Param("semester")String semester);

    @Select("select * from Sc where stuId=#{id}")
    List<Sc> search_select_course(@Param("id") String id);

    @Select("select * from course where year = #{year} and semster = #{semester} and weekdata = #{weekdata} and courseOrder in (select courseOrder FROM sc WHERE courseId in (select courseId from sc where stuId = #{stuId}))")
    List<Course> search_today(@Param("year") String year, @Param("semester") String semester, @Param("weekdata") String weekdata, @Param("stuId") String stuId);

    @Insert("insert into Sc values(#{stuId},#{courseId},#{courseOrder},#{myyear},#{semester},#{grade},#{state})")
    int insert(Sc profession);

    @Delete("delete from Sc where course_id = #{courseId} and course_order=#{courseOrder} and myyear = #{myyear} and semester = #{semester}")
    int delete_profession(Sc p);

    @Update("update set Sc grade = #{grade} where course_id = #{courseId} and course_order=#{courseOrder} and myyear = #{myyear} and semester = #{semester}")
    int update_grade(Sc p);

    @Update("update set Sc state = #{state} where course_id = #{courseId} and course_order=#{courseOrder} and myyear = #{myyear} and semester = #{semester}")
    int update_state(Sc p);
}
