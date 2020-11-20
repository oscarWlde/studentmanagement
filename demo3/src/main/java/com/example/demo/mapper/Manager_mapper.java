package com.example.demo.mapper;

import com.example.demo.bean.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Manager_mapper {
    @Select("select * from manager where managerId=#{managerId} and code = #{code}")
    Manager findBymanid(@Param("managerId") String managerId, @Param("code") String code);
}
