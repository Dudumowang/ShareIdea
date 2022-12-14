package com.example.springboot.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.controller.dto.UserDTOWithoutDate;
import com.example.springboot.eneity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserDTO login(String id, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password",password);
        User user;
        try{
            user=userMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(user!=null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setAddress(user.getAddress());
            String token= TokenUtils.genToken(user.getId(),user.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    public User selectByID(String id){
           User t;
           int affect;
           try{
               QueryWrapper<User> queryWrapper=new QueryWrapper<>();
               queryWrapper.eq("id",id);
               t=userMapper.selectOne(queryWrapper);
           }catch (Exception e){
               throw new ServiceException(Constants.CODE_500,"系统错误");
           }
           if(t==null){
               throw new ServiceException(Constants.CODE_600,"参数错误");
           }
           else{
               return t;
           }
    }

    public boolean insert(User user){
        int affect;
        User temp;
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",user.getId());
            temp=userMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(temp!=null){
            throw new ServiceException(Constants.CODE_600,"ID已存在");
        }
        //当前数据库中没有该ID
        else{
            try{
                affect=userMapper.insert(user);
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
    }

    public boolean update(User user){
        User temp;
        int affect;
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",user.getId()).eq("password",user.getPassword());
            temp=userMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误123");
        }
        if(temp==null){
            throw new ServiceException(Constants.CODE_600,"密码错误");
        }
        //经过校验密码正确
        else {
            try {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",user.getId());
                affect = userMapper.update(user, queryWrapper);
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
    }

    public boolean updateWithoutPassword(User user){
        int affect;
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",user.getId());
            affect = userMapper.update(user, queryWrapper);
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

    public boolean delete(User user){
        int affect;
        User temp;
        System.out.println(user);
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",user.getId());
            affect = userMapper.delete(queryWrapper);
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

    public Map<String, Object> findPage(Integer pageNum, Integer pageSize,
                                        String id,String username,String phone,String email) {
        pageNum=(pageNum-1)*pageSize;
        List<User> data;
        Integer total;

        System.out.println(id);
        id="%"+id+"%";
        username="%"+username+"%";
        phone="%"+phone+"%";
        email="%"+email+"%";
        System.out.println("id:\n");
        System.out.println(id);

        try {
            data=userMapper.selectPage(pageNum,pageSize,id,username,phone,email);
            System.out.println(data);
            total = userMapper.selectTotal(id,username,phone,email);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        Map<String, Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);

        return res;
    }

    public int UserTotal(){
        int t;
        try{
            t=userMapper.Total();
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return t;
    }
}
