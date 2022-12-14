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
     * ????????????
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // ????????????????????????????????????
        List<Admin> list = adminService.list();
        // ?????????????????????writer ?????????????????????
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/????????????.xlsx");
        // ????????????????????????????????????
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //?????????????????????
        writer.addHeaderAlias("id", "??????ID");
        writer.addHeaderAlias("name", "????????????");
        writer.addHeaderAlias("password", "??????");
        writer.addHeaderAlias("email", "??????");
        writer.addHeaderAlias("mobile", "?????????");

        // ???????????????list???????????????excel??????????????????????????????????????????
        writer.write(list, true);

        // ??????????????????????????????
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("???????????????", "UTF-8");
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
        // ??????1???(??????) ?????? javabean???????????????Excel??????????????????????????????????????????????????????javabean????????????????????????
//        List<User> list = reader.readAll(User.class);

        // ??????2???????????????????????????????????????????????????
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
