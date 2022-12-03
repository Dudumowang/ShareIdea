package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.eneity.Comment;
import com.example.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.List;
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

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        return Result.success(commentService.removeById(id));
    }

    @GetMapping("/tree/{ideaId}")
    @ResponseBody
    public Result findTree (@PathVariable Integer ideaId){

        List<Comment> comments = commentService.findCommentDetail(ideaId);


        return Result.success(comments);
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestBody Comment comment) {
        if (comment.getContent() == null) {
            return Result.error(Constants.CODE_500, "评论不能为空！");
        }
        return Result.success(commentService.saveOrUpdate(comment));
    }
}
