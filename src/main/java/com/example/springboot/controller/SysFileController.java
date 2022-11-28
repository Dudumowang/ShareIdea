package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.eneity.SysFile;
import com.example.springboot.mapper.SysFileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class SysFileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private SysFileMapper sysFileMapper;

    /**
     * 文件上传接口 http://localhost:9090/file/upload
     * @param file
     * @return url
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException{
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        long size = file.getSize();

        // 存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        // 判断配置的文件目录是否存在， 若不存在则创建一个新目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        String url = "http://localhost:9090/file/" + fileUUID;
        File uploadFile = new File(fileUploadPath + fileUUID);

        // 获取文件的md5; 如果数据库中已有该md5， 则不进行数据库存储
        String md5 = SecureUtil.md5(file.getInputStream());

        // 把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);

        // 存储数据库
        SysFile saveFile = new SysFile();
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setName(originalFileName);
        saveFile.setMd5(md5);
        saveFile.setUrl(url);
        sysFileMapper.insert(saveFile);
        return url;
    }

    @GetMapping("/{fileUUID}")
    private void download(@PathVariable String fileUUID, HttpServletResponse response) throws  IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);

        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
