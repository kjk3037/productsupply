package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;

@RequestMapping("/acti")
@RestController
public class ActController {
    @Autowired
    ActProcessService actProcessService;
    @Autowired
    ActTaskService actTaskService;
    @RequestMapping("/create")
    public Message create(){
        actProcessService.createInst("cc","5");
        return Message.success("创建成功");
    }
    @RequestMapping("/execute")
    public void execute(String instId){
        actTaskService.execute(instId);
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
