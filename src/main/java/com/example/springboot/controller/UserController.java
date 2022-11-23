package com.example.springboot.controller;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.eneity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result login(@RequestParam String id,@RequestParam String password){
        UserDTO userDTO=userService.login(id,password);
        return Result.success(userDTO);
    }

    @PostMapping("/insert")
    public Result save(@RequestBody User user) {
        boolean t;
        t=userService.insert(user);
        return Result.success(t);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        boolean t;
        t=userService.update(user);
        return Result.success(t);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user){
        System.out.println(user.toString());
        boolean t =userService.delete(user);
        return Result.success(t);
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String id,
                           @RequestParam (defaultValue = "") String username,
                           @RequestParam (defaultValue = "") String phone,
                           @RequestParam (defaultValue = "") String email){
        Map<String, Object> res=new HashMap<>();
        System.out.println("Controller:id");
        System.out.println(id);
        System.out.println(username);
        System.out.println(phone);
        System.out.println(email);
        res=userService.findPage(pageNum,pageSize,id,username,phone,email);
        return Result.success(res);
    }

    @PostMapping("/adminUpdate")
    public Result adminUpdate(@RequestBody User user){
        boolean t;
        t=userService.updateWithoutPassword(user);
        return Result.success(t);
    }
}
