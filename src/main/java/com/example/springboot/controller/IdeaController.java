package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.IdeaDTO;
import com.example.springboot.eneity.Idea;
import com.example.springboot.mapper.IdeaMapper;
import com.example.springboot.service.IdeaService;
import io.github.classgraph.json.Id;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id){
        boolean t= ideaService.delete(id);
        return Result.success(t);
    }

    @PostMapping("/insert")
    public Result delete(@RequestBody Idea idea){
        boolean t= ideaService.insert(idea);
        return Result.success(t);
    }

    @PostMapping("/updateTitle")
    public Result updateTitle(@RequestBody IdeaDTO ideaDTO){
        boolean t=ideaService.updateTitle(ideaDTO.getId(), ideaDTO.getTitle());
        return Result.success(t);
    }

    @PostMapping("/updateContent")
    public Result updateContent(@RequestBody IdeaDTO ideaDTO){
        boolean t=ideaService.updateContent(ideaDTO.getId(), ideaDTO.getContent());
        return Result.success(t);
    }

}
