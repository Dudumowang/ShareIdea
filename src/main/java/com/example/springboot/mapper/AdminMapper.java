package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.eneity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * from admin")
    List<Admin> findAll();

    @Select("select * from admin" +
            " where id like #{id} " +
            "and name like #{name} " +
            "and mobile like #{mobile} " +
            "and email like #{email} " +
            " limit #{pageNum},#{pageSize}")
    List<Admin> selectPage(Integer pageNum, Integer pageSize,String id,String name,String mobile,String email);

    @Select("select count(*) from admin where id like #{id} and name like #{name} and mobile like #{mobile} and email like #{email}")
    Integer selectTotal(String id,String name,String mobile,String email);

    @Select("select count(*) from admin")
    Integer Total();
}
