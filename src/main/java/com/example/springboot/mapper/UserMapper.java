package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.eneity.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{
    @Select("SELECT * from user")
    List<User> findAll();

    @Select("select * from user" +
            " where id like #{id} " +
            "and username like #{username} " +
            "and phone like #{phone} " +
            "and email like #{email} " +
            " limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize,String id,String username,String phone,String email);

    @Select("select count(*) from user")
    Integer selectTotal();




}
