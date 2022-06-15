package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.Attachment;
import cn.zq.pojo.User;
import cn.zq.service.AttachmentService;
import cn.zq.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/attachment")
@Slf4j
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    /*
    * 多附件上传
    * */
    @PostMapping("/uploads")
    public Message uploads(@RequestParam("files") List<MultipartFile> files, @RequestParam String businessKey, @RequestParam String business ) {
        for (MultipartFile file : files) {
            try {
                InputStream is = file.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                IOUtils.copy(is, os);
                byte[] bytes = os.toByteArray();
                String end = "";
                System.out.println(file.getContentType());
//                File newFile = new File("E:/kjk/project/java/productsupply/file/" + file.getOriginalFilename());
                File newFile = new File("C:/MyDocument/myJavaProject/productsupply/file/" + file.getOriginalFilename());
                File finalFile= FileUtils.createFile(newFile,0);
                OutputStream outputStream = new FileOutputStream(finalFile);
                outputStream.write(bytes);
                //附件信息插入数据库
                Attachment attachment = new Attachment();
                attachment.setPath(newFile.getPath());
                attachment.setBusinessKey(businessKey);
                attachment.setBusiness(business);
                attachmentService.save(attachment);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传发生异常 -> {}", e.getMessage());
                return Message.failed("文件上传失败");
            }
        }
//        HashMap<String, List<MultipartFile>> stringListHashMap = new HashMap<>();
//        stringListHashMap.put("attachment",files);


//        Boolean uploads = attachmentService.uploads(files, business, businessKey);
//        if (!uploads){
//            return Message.failed("上传失败");
//        }
        return Message.success("上传成功");
    }
    /*
    * 单附件上传（暂定弃用）
    * */
    /*@PostMapping("/upload")
    public Message upload(@RequestParam("file") MultipartFile file, @RequestParam String bussinessKey, @RequestParam String bussiness) {
        System.out.println(bussinessKey);
        System.out.println(bussiness);
        System.out.println(file);
        try {
            InputStream is = file.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            IOUtils.copy(is, os);
            byte[] bytes = os.toByteArray();
            String end = "";
            //将附件输出到相关目录
            File newFile = new File("E:/kjk/project/java/productsupply/file/" + file.getOriginalFilename());
            File finalFile=FileUtils.createFile(newFile,0);
            OutputStream outputStream = new FileOutputStream(finalFile);
            outputStream.write(bytes);
            //附件信息插入数据库
            Attachment attachment = new Attachment();
            attachment.setPath(newFile.getPath());
            attachment.setBussinessKey(bussinessKey);
            attachment.setBussiness(bussiness);
            attachmentService.save(attachment);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传发生异常 -> {}", e.getMessage());
            return Message.failed("文件上传失败");
        }
        return Message.success("上传成功");
    }*/
    @GetMapping("/getFilesForBusiness")
    public void getFilesForBusiness(String business,String businessKey,String businessField,HttpServletRequest request, HttpServletResponse response){
        QueryWrapper<Attachment> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("business_key",businessKey).eq("business",business).eq("business_field",businessField);
        List<Attachment> attachments = attachmentService.list(objectQueryWrapper);
        try {
            for (Attachment attachment : attachments) {
                attachmentService.getFile(attachment.getPath(), response);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/getFile")
    public void getFile(String url,HttpServletRequest request, HttpServletResponse response){
        try {
            attachmentService.getFile(url, response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

