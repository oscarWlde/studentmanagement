package com.example.demo.mapper;

import com.example.demo.bean.Awardpunishment;
import com.example.demo.util.award.return_award;
import com.example.demo.util.award.select_award;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Award_mapper {
    @Select("select distinct awardId,gatAward.awardName,gatAward.tag,state,time,content,descript from gatAward \n" +
            "left join awardpunishment on awardpunishment.awardName = gatAward.awardName\n" +
            "where stuId = #{stuId};")
    List<return_award> award(String stuId);//test

    @Select("select awardId,gatAward.awardName,gatAward.tag,descript, from gatAward \n" +
            "left join awardpunishment on awardpunishment.awardName = gatAward.awardName\n" +
            "")
    List<return_award> allaward();//test

    @Insert("insert into gataward values(#{stuId},#{awardName},#{tag},#{state},#{descript},#{time})")
    int insert_award(@Param("stuId")String stuId,@Param("awardName")String awardName,@Param("tag")String tag,@Param("state")String state,@Param("descript")String descript,@Param("time")String time);

    @Select("select * from awardpunishment where tag='1'")
    List<Awardpunishment> get_allaward();

    @Select("select student.stuId,student.stuName,awardName,descript,gataward.time\n" +
            "from gataward,student\n" +
            "where student.stuId = gataward.stuId\n" +
            " and state = #{state} and tag='1'")
    List<select_award> select_award( @Param("state")String state);

    @Update("update gataward set state =#{state}  where stuId = #{stuId}\n" +
            "and state = '0' \n" +
            "and awardName = #{awardName}\n" +
            "and time = #{time}")
    int update_award(@Param("stuId")String stuId,@Param("state")String state,@Param("awardName")String awardName,@Param("time")String time);

}
