package com.example.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.IdeaDTO;
import com.example.springboot.eneity.Idea;
import com.example.springboot.eneity.User;
import com.example.springboot.mapper.IdeaMapper;
import com.example.springboot.service.IdeaService;
import com.example.springboot.utils.TokenUtils;
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

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestBody Idea idea, @RequestBody User user){
        if (idea.getId() == null) {
            idea.setPubTime(DateUtil.now());
            idea.setUid(user.getUsername());
        } else {
            idea.setFinTime(DateUtil.now());
        }

        return Result.success(ideaService.saveOrUpdate(idea));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String userid,
                           @RequestParam (defaultValue = "") String title){
        Map<String, Object> res=new HashMap<>();
        res=ideaService.findPage(pageNum,pageSize,userid,title);
        return Result.success(res);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result findById(@PathVariable Integer id){
        return Result.success(ideaService.getById(id));
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
