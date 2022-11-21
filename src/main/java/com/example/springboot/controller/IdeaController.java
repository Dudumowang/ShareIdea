package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.eneity.Idea;
import com.example.springboot.mapper.IdeaMapper;
import com.example.springboot.service.IdeaService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    @Autowired
    private IdeaMapper ideaMapper;

    @Autowired
    private IdeaService ideaService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Map<String, Object> res=new HashMap<>();
        res=ideaService.findPage(pageNum,pageSize);
        return Result.success(res);
    }

    @GetMapping("/test")
    public Result test(@RequestParam Integer id){
        Idea idea= ideaMapper.selectById(id);
        System.out.println(idea);
        return Result.success(idea);
    }


}
