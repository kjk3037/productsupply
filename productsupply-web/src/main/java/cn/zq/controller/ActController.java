package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import cn.zq.utils.FileUtils;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/acti")
@Slf4j
@RestController
public class ActController {
    @Autowired
    ActProcessService actProcessService;
    @Autowired
    ActTaskService actTaskService;
    @RequestMapping("/create")
    public Message create(){
        actProcessService.startProcess("cc","5",new HashMap());
        return Message.success("创建成功");
    }
    @RequestMapping("/execute")
    public void execute(String instId){
        actTaskService.execute(instId,"");
    }
    @RequestMapping("/deploy")
    public void deploy(){
        actProcessService.deployProcess();
    }
    @RequestMapping("/rollback")
    public void rollback(String instId,String currentTaskId,String targetTaskId) throws Exception {
        actTaskService.rollback(instId,currentTaskId, targetTaskId);
    }
    @PostMapping("/upload")
    public Message upload(@RequestBody List<MultipartFile> files) {
        System.out.println(files);
        for (MultipartFile file : files) {
            try {
                InputStream is = file.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                IOUtils.copy(is, os);
                byte[] bytes = os.toByteArray();
                String end = "";
                System.out.println(file.getContentType());
                File newFile = new File("E:/kjk/project/java/productsupply/file/" + file.getOriginalFilename());
                File finalFile=FileUtils.createFile(newFile,0);
                OutputStream outputStream = new FileOutputStream(finalFile);
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传发生异常 -> {}", e.getMessage());
                return Message.failed("文件上传失败");
            }
        }
        return Message.success("上传成功");
    }
}
