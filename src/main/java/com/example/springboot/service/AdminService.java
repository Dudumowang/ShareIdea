package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.AdminDTO;
import com.example.springboot.eneity.Admin;
import com.example.springboot.eneity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public AdminDTO login(String id, String password){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password",password);
        Admin admin;
        try{
            admin=adminMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(admin!=null){
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(admin.getId());
            adminDTO.setEmail(admin.getEmail());
            adminDTO.setMobile(admin.getMobile());
            String token= TokenUtils.genToken(admin.getId(),admin.getPassword());
            adminDTO.setToken(token);
            return adminDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    public Admin selectById(String id){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Admin admin;
        try {
            admin=adminMapper.selectOne(queryWrapper);
        }catch(Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(admin!=null){
            return admin;
        }else{
            throw new ServiceException(Constants.CODE_600,"ID异常");
        }

    }

    public boolean updateInformation(Admin admin){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",admin.getId()).eq("password",admin.getPassword());
        int affect=0;
        try {
            affect=adminMapper.update(admin,queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统异常");
        }
        if(affect>0){
            return true;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"密码错误");
        }


    }

    public boolean changePassword(String id,String oldPassword,String newPassword){
        Admin admin;
        admin = this.selectById(id);
        int affect=0;
        if(admin==null){
            throw new ServiceException(Constants.CODE_600,"参数错误");
        }
        else{
            if(!admin.getPassword().equals(oldPassword)){
                throw new ServiceException(Constants.CODE_600,"原密码错误");
            }
            else{
                admin.setPassword(newPassword);
                QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",admin.getId());
                try{
                    affect=adminMapper.update(admin,queryWrapper);
                }catch (Exception e){
                    throw new ServiceException(Constants.CODE_500,"系统错误");
                }
                if(affect>0){return true;}
                else{
                    throw new ServiceException(Constants.CODE_600,"更新失败");
                }
            }
        }


    }


}
