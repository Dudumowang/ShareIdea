package com.example.springboot.service;

import com.example.springboot.common.Constants;
import com.example.springboot.eneity.Idea;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.IdeaMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IdeaService {

    @Autowired
    private IdeaMapper ideaMapper;

    public Map<String, Object> findPage(Integer pageNum, Integer pageSize,String userid,String title){
        pageNum=(pageNum-1)*pageSize;
        List<Idea> data;
        Integer total;
        userid="%"+userid+"%";
        title="%"+title+"%";
        System.out.println(userid);
        System.out.println(title);
        try {
            data=ideaMapper.selectPage(pageNum,pageSize,userid,title);
            System.out.println(data);
            total = ideaMapper.selectTotal(userid,title);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        Map<String, Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    public boolean delete(Integer id){
        int affect=0;
        try{
            affect=ideaMapper.deleteById(id);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(affect>0){
            return true;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"Idea的ID出现错误");
        }
    }

    public boolean insert(Idea idea){
        idea.setId(null);
        idea.setStars(0);
        idea.setPubTime(null);
        idea.setFinTime(null);
        int affect=0;
        try{
            affect=ideaMapper.insert(idea);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(affect>0){
            return true;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"参数错误");
        }
    }

    public boolean updateTitle(Integer id,String title){
        int affect;
        try{
            affect=ideaMapper.updateTitle(id,title);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(affect>0){
            return true;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"更改失败");
        }
    }

    public boolean updateContent(Integer id,String content){
        int affect;
        try{
            affect=ideaMapper.updateContent(id,content);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(affect>0){
            return true;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"更改失败");
        }
    }

    public int IdeaTotal(){
        int t;
        try{
            t=ideaMapper.Total();
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return t;
    }
}
