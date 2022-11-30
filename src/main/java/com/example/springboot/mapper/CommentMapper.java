package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.eneity.Comment;
import com.example.springboot.eneity.Idea;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comment where pubId like #{userId} and reId like #{ideaId} limit #{pageNum}, #{pageSize}")
    List<Comment> selectPage(Integer pageNum, Integer pageSize,String userId,String ideaId);

    @Select("select count(*) from comment where pubId like #{userId} and reId like #{ideaId} ")
    Integer selectTotal(String userId,String ideaId);

    @Select("select count(*) from comment")
    Integer Total();
}
