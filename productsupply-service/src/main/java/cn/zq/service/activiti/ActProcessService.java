package cn.zq.service.activiti;

import cn.zq.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ActProcessService {
    @Autowired
    ActTaskService taskService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RuntimeService runtimeService;
    /*
    * @describe部署流程
    * */
    public void deployProcess(){
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("processes/saleOrder.bpmn20.xml").name("saleOrder").deploy();
        System.out.println("id: "+deploy.getId());
        System.out.println("name: "+deploy.getName());
    }
    /*
    *@describe 开启流程创建实例
    *@param String:processKey , String:bussinessKey
    **/
    public void startProcess(String processKey,String businessKey){
        String username= ShiroUtils.getUsername();
        Map<String,Object> args=new HashMap<>();
        args.put("businessKey",businessKey);
        args.put("user",username);
        //args.put("leader","jack");
        ProcessInstance test = runtimeService.startProcessInstanceByKey(processKey,businessKey,args);
//        System.out.println("ProcessInstId:"+test.getProcessInstanceId());
//        System.out.println("ProcessDefId:"+test.getProcessDefinitionId());
//        System.out.println("Id:"+test.getId());
        //执行申请节点
        taskService.execute(test.getProcessInstanceId(),"");
        taskService.addCandidateUserByBusinessKey(businessKey,"kjk");
        taskService.addCandidateUserByBusinessKey(businessKey,"root");
        log.info("ProcessInstance is created");
    }

    /*
    *@describe 获取流程实例历史节点
    *@param String:instId
    **/
    public void getHisActivities(String instId){
        List<HistoricIdentityLink> linkList = historyService.getHistoricIdentityLinksForProcessInstance(instId);
        Iterator<HistoricIdentityLink> iterator = linkList.iterator();
        while (iterator.hasNext()){
            HistoricIdentityLink next = iterator.next();
            System.out.println("taskId:"+next.getTaskId());
            System.out.println("instId:"+next.getProcessInstanceId());
            System.out.println("userId:"+next.getUserId());
            System.out.println("type:"+next.getType());
        }
    }
}
