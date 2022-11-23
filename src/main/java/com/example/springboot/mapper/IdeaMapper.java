package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.eneity.Idea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IdeaMapper extends BaseMapper<Idea> {

    @Select("select * from idea where uid like #{userid} and title like #{title} limit #{pageNum}, #{pageSize}")
    List<Idea> selectPage(Integer pageNum, Integer pageSize,String userid,String title);


    @Select("select count(*) from idea")
    Integer selectTotal();

    @Update("update set title=#{title} where id=#{id}")
    int updateTitle(Integer id,String title);

    @Update("update set content=#{content} where id=#{id}")
    int updateContent(Integer id,String content);
}
