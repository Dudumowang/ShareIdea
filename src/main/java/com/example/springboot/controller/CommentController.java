package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String userId,
                           @RequestParam (defaultValue = "") String ideaId){
        Map<String, Object> res=new HashMap<>();
        res=commentService.findPage(pageNum,pageSize,userId, ideaId);
        return Result.success(res);
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id){
        boolean t;
        t= commentService.delete(id);
        return Result.success(t);
    }

    @GetMapping("/total")
    public Result CommentTotal(){
        int res;
        res=commentService.CommentTotal();
        return Result.success(res);
    }
}
