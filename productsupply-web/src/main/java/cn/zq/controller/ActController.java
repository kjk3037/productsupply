package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;

import cn.zq.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;


import org.apache.shiro.crypto.hash.Hash;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
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
    /*
    * 开启流程接口
    * */
//    @RequestMapping("/create")
//    public Message create(){
//        actProcessService.startProcess("","5");
//        return Message.success("创建成功");
//    }
    /*
    * 执行接口
    * */
    @RequestMapping("/execute")
    public void execute(String instId){
        actTaskService.execute(instId,"");
    }
    /*
    * 流程部署接口
    * */
    @RequestMapping("/deploy")
    public void deploy(){
        actProcessService.deployProcess();
    }
    /*
    * 退回接口
    * */
    @RequestMapping("/rollback")
    public void rollback(String instId,String currentTaskId,String targetTaskId) throws Exception {
        actTaskService.rollback(instId,currentTaskId, targetTaskId);
    }
    /*
    * 获取当前用户所有待办任务数据
    * */
    @GetMapping("getTodolist")
    public Message getTodolist(){
        List list = actTaskService.getAllTodoList();
        return Message.success(list,"待办列表获取成功");
    }
    /*
     * 获取当前用户所有待办任务数据
     * */
    @GetMapping("/test")
    public Message test(){
        RedisUtils.generFieldStatus();
        return Message.success(111);
    }
}
