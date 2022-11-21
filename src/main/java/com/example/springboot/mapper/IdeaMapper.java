package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.eneity.Idea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IdeaMapper extends BaseMapper<Idea> {

    @Select("select * from idea limit #{pageNum}, #{pageSize}")
    List<Idea> selectPage(Integer pageNum, Integer pageSize);


    @Select("select count(*) from idea")
    Integer selectTotal();

    @Select("select * from idea where id=123557")
    Idea selectById(Integer id);
}
