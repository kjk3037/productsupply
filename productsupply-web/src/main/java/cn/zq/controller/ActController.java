package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.*;
import java.util.HashMap;
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
    @CrossOrigin
    @PostMapping("/uploadByOne")
    public Message uploadByOne(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            IOUtils.copy(is, os);
            byte[] bytes = os.toByteArray();
            File file1=new File("C:/MyF");
            OutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(bytes);
            return Message.success("111");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传发生异常 -> {}", e.getMessage());
            return Message.failed("文件上传失败");
        }
    }

}
