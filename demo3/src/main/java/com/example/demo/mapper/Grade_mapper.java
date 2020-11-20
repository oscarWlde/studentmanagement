package com.example.demo.mapper;

import com.example.demo.util.student.return_grade;
import com.example.demo.util.student.return_search_grade;
import com.example.demo.util.student.return_totalcredit;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Grade_mapper {
    @Select("select course.courseId,courseName,teacher.teacherName,credit,pgrade,grade,totalgrade from sc \n" +
            "left join course on course.courseNum = sc.courseNum  \n" +
            "left join teacher on teacher.teacherName = course.teacherName  \n" +
            "where stuId = #{id} ;\n")
    List<return_grade> load_grade(String id);

    @Select("select course.courseId,courseName,teacher.teacherName,credit,pgrade,grade,totalgrade from sc \n" +
            "left join course on course.courseNum = sc.courseNum  \n" +
            "left join teacher on teacher.teacherName = course.teacherName  \n" +
            "where stuId = #{id} and myyear = #{year} and sc.semster = #{semster};\n")
    List<return_search_grade> search_grade(String id, String year,String semster);

    @Select("select totalcredit from student where stuId = #{id};")
    List<return_totalcredit> search_credit(String id);

}
