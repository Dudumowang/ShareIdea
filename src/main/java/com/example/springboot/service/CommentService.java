package com.example.springboot.service;


import com.example.springboot.common.Constants;
import com.example.springboot.eneity.Comment;
import com.example.springboot.eneity.Idea;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    Comment selectById(Integer id){
        Comment comment;
        try{
            comment=commentMapper.selectById(id);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(comment!=null){
            return comment;
        }
        else{
           throw new ServiceException(Constants.CODE_600,"参数错误");
        }
    }

    public Map<String, Object> findPage(Integer pageNum, Integer pageSize){
        pageNum=(pageNum-1)*pageSize;
        List<Idea> data;
        Integer total;
        try {
            data=commentMapper.selectPage(pageNum,pageSize);
            System.out.println(data);
            total = commentMapper.selectTotal();
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        Map<String, Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

}
