package com.example.springboot.controller;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.AdminDTO;
import com.example.springboot.controller.dto.AdminPasswordDTO;
import com.example.springboot.eneity.Admin;
import com.example.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public Result login(@RequestParam String id, @RequestParam String password){
        System.out.println(id);
        System.out.println(password);
        AdminDTO adminDTO = adminService.login(id,password);
        return Result.success(adminDTO);
    }

    @GetMapping("/select")
    public Result select(@RequestParam String id){
        Admin admin=adminService.selectById(id);
        admin.setPassword("");
        System.out.println(admin.toString());
        return Result.success(admin);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        boolean t;
        t= adminService.updateInformation(admin);
        return Result.success(t);
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody AdminPasswordDTO adminPasswordDTO) {
        boolean t;
        Admin admin;
        t = adminService.changePassword(adminPasswordDTO.getId(),adminPasswordDTO.getOldPassword(),adminPasswordDTO.getNewPassword());
        return Result.success(t);
    }
}
