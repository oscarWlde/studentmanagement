package com.example.demo.mapper;


import com.example.demo.bean.Take;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Take_map {

    @Select("select * from take")
    List<Take> get_take();

    @Select("select * from take where classroomId=#{classroomId}")
    Take get_specific(@Param("classroomId") String classroomId);

    @Select("select * from take where takeId=#{takeId} and type=#{type}")
    List<Take> select_classroom(@Param("takeId") String takeId, @Param("type") String type);

    @Select("select * from take where takeId=#{takeId} and classroomId=#{classroomId} and year=#{year} and week=#{week}")
    List<Take> search_classroom(@Param("takeId") String takeId, @Param("classroomId") String classroom, @Param("year") String year, @Param("week") String week);

    @Select("select * from take where (year=#{year} and week=#{week} and semester=#{semester}) or (type='1' and year=#{year} and semester=#{semester} and week>=#{week})")
    List<Take> search_classroom1(@Param("year") String year, @Param("week") String week,@Param("semester") String semester);

    @Select("select * from take where classroomId in (select classroomId from classroom where buildingName= #{buildingName}) and ((year = #{year} and week = #{week} and semester=#{semester}) or (type='1' and year = #{year} and semester=#{semester} and week>=#{week}))")
    List<Take> search_classroom2(@Param("buildingName") String buildingName, @Param("week") String week, @Param("year") String year,@Param("semester") String semester);

    @Select("select * from take where classroomId in (select classroomId from classroom where buildingName= #{buildingName}) and (year = #{year} and week = #{week} and semester=#{semester})")
    List<Take> search_classroom3(@Param("buildingName") String buildingName, @Param("week") String week, @Param("year") String year,@Param("semester") String semester);


    @Insert("insert into take(takeId,day,week,year,classroomId,takeDate,startTime,endTime,type,information,semester) values(#{takeId},#{day},#{week},#{year},#{classroomId},#{takeDate},#{startTime},#{endTime},#{type},#{information},#{semster})")
    void addtake(Take take);

    @Delete("delete from take where takeId=#{takeId} and classroomId=#{classroomId} and takeDate=#{takeDate} and startTime = #{starttime} and endTime=#{endtime} and week=#{week} and day=#{day}")
    int delete(@Param("takeId") String takeid, @Param("classroomId") String classroomId, @Param("takeDate") String takeDate, @Param("starttime") String start_time, @Param("endtime") String end_time, @Param("week") String week, @Param("day") String day);

    @Update("update take set purpose = #{purpose} where take_id=#{takeId} and classroom_id=#{classroomId} and take_date=#{takeDate} and take_time = #{takeTime}")
    int update_purpose(Take t);



}
