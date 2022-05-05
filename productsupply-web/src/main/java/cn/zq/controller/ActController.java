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

}
