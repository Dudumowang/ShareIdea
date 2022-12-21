package com.example.springboot.controller;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.AdminDTO;
import com.example.springboot.controller.dto.AdminPasswordDTO;
import com.example.springboot.eneity.Admin;
import com.example.springboot.eneity.Files;
import com.example.springboot.eneity.User;
import com.example.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController<file> {

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

    @PostMapping("/insert")
    public Result insert(@RequestBody Admin admin) {
        boolean t;
        t=adminService.insert(admin);
        return Result.success(t);
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

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String id,
                           @RequestParam (defaultValue = "") String name,
                           @RequestParam (defaultValue = "") String mobile,
                           @RequestParam (defaultValue = "") String email){
        Map<String, Object> res=new HashMap<>();
        System.out.println("Controller:id");
        System.out.println(id);
        System.out.println(name);
        System.out.println(mobile);
        System.out.println(email);
        res=adminService.findPage(pageNum,pageSize,id,name,mobile,email);
        return Result.success(res);
    }

    @GetMapping("/total")
    public Result AdminTotal(){
        int res;
        res=adminService.AdminTotal();
        return Result.success(res);
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Admin> list = adminService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("id", "账号ID");
        writer.addHeaderAlias("name", "管理员名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("mobile", "手机号");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("管理员信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<Admin> admins = CollUtil.newArrayList();
        for (List<Object> row : list) {
            Admin admin = new Admin();
            admin.setId(row.get(0).toString());
            admin.setName(row.get(1).toString());
            admin.setPassword(row.get(2).toString());
            admin.setEmail(row.get(3).toString());
            admin.setMobile(row.get(4).toString());
            admins.add(admin);
            adminService.insert(admin);
        }

        //adminService.saveBatch(admins);
        return true;
    }

}
