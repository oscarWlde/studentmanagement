package com.example.demo.mapper;

import com.example.demo.bean.Classroom;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Classroom_map {
    @Select("select * from classroom")
    List<Classroom> get_classroom();

    @Select("select * from classroom where classroomId=#{classroomId}")
    List<Classroom> get_specificroom(@Param("classroomId") String classroomId);

    @Select("select * from classroom where buildingName=#{buildingname}")
    List<Classroom> search_classroom(@Param("buildingname") String buildingname);

    @Insert("insert into Classroom values(#{classroomId},#{floor},#{capacity},#{buildingName}")
    int add_classroom(Classroom classroom);

    @Delete("delete from Classroom where classroom_id = #{classroomid}")
    int delete_classroom(Classroom classroom);

    @Update("update Classroom set capacity = #{capacity} where classroom_id = #{classroomid}")
    int update_capacity(Classroom classroom);


}