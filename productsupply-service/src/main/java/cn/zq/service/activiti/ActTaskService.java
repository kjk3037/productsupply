package cn.zq.service.activiti;

import cn.zq.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ActTaskService {
    @Autowired
    TaskService taskService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RuntimeService runtimeService;
    /*
     *
     * */
    /*
    *@describe 执行操作节点(无流程参数)
    *@param 流程实例Id
    **/
    public void execute(String instId){
        //System.out.println("执行"+instId+"节点");
        Task task = taskService.createTaskQuery()
                .processInstanceId(instId)
                .singleResult();
        if (task!=null){
            taskService.complete(task.getId());
            log.info("task: "+task.getId()+" is completed!" );
        }
    }

    /*
    *@describe 执行操作节点(含流程参数)
    *@param 流程实例Id,流程变量
    **/
    public void execute(String instId, Map<String,Object> args){
        //System.out.println("执行"+instId+"节点");
        Task task = taskService.createTaskQuery()
                .processInstanceId(instId)
                .singleResult();
        if (task!=null){
            taskService.complete(task.getId(),args);
            log.info("task: "+task.getId()+" is completed!" );
        }
    }
    /*
     *
     * */
    /*
    *@describe 获取当前用户所有待办节点
    *@param
    **/
    public List<Task> getTasksByAss(){
        String username = ShiroUtils.getUsername();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(username)
                .list();

        System.out.println("size:"+tasks.size());
        for(Task task:tasks){
            if (task!=null){
                System.out.println("taskId:"+task.getId());
                System.out.println("taskName:"+task.getName());
                System.out.println("taskAssignee:"+task.getAssignee());
            }
        }
        if (tasks==null||tasks.isEmpty()){
            log.info("not find any task by username:"+username);
            return null;
        }
        return tasks;

    }
    /*
    *@describe 根据实例ID获取当前任务信息(确定单一流程节点)
    *@param 流程实例Id
    **/
    public Task getTaskById(String id){
        System.out.println("执行getInstById");
        Task task = taskService.createTaskQuery()
                .processInstanceId(id)
                .singleResult();

        if (task!=null){
            System.out.println("task:"+task.toString());
            System.out.println("taskName:"+task.getName());
            System.out.println("taskAssignee:"+task.getAssignee());
            return task;
        }
        log.info("not find task by taskId:" +id);
        return null;
    }

    /*
    *@describe 流程撤回
    *@param processInstId:流程实例ID，现用户
    **/
    /*public void revoke(String processInstanceId, String nowUser) throws Exception {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            throw new Exception("流程未启动或已执行完成，无法撤回");
        }
        //通过processInstanceId查询历史节点
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime()
                .asc()
                .list();
        String myTaskId = null;
        HistoricTaskInstance myTask = null;
        //找到当前运行的节点
        for (HistoricTaskInstance hti : htiList) {
            if (nowUser.equals(hti.getAssignee())) {
                myTaskId = hti.getId();
                myTask = hti;
                break;
            }
        }
        if (null == myTaskId) {
            throw new Exception("该任务非当前用户提交，无法撤回");
        }
        String processDefinitionId = myTask.getProcessDefinitionId();
        //获取流程模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        String myActivityId = null;
        //查询已经完成的流程节点，查询到上一条已完成的节点，则跳出循环
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
                .executionId(myTask.getExecutionId()).finished().list();
        for (HistoricActivityInstance hai : haiList) {
            if (myTaskId.equals(hai.getTaskId())) {
                myActivityId = hai.getActivityId();
                break;
            }
        }
        FlowNode myFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(myActivityId);
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        //记录原活动方向
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(flowNode.getOutgoingFlows());
        //清理活动方向
        flowNode.getOutgoingFlows().clear();
        //建立新方向
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newSequenceFlowId");
        newSequenceFlow.setSourceFlowElement(flowNode);
        newSequenceFlow.setTargetFlowElement(myFlowNode);
        newSequenceFlowList.add(newSequenceFlow);
        flowNode.setOutgoingFlows(newSequenceFlowList);
        Authentication.setAuthenticatedUserId(nowUser);
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "撤回");
        //完成任务
        taskService.complete(task.getId());
        //恢复原方向
        flowNode.setOutgoingFlows(oriSequenceFlows);
        log.info("撤回成功！");
    }*/

    /*
    *@describe 节点退回
    *@param
    **/
    public void rollback(String processInstanceId, String currentTaskId,String targetTaskId) throws Exception {
        //获取当前任务
        Task task = taskService.createTaskQuery().taskId(currentTaskId).singleResult();
        if (task == null) {
            throw new Exception("流程未启动或已执行完成，无法撤回");
        }
        //通过processInstanceId查询历史节点
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime()
                .asc()
                .list();
        HistoricTaskInstance myTask = null;
        //找到目标运行的节点
        for (HistoricTaskInstance hti : htiList) {
            if (targetTaskId.equals(hti.getId())) {
                myTask = hti;
                break;
            }
        }
        String processDefinitionId = myTask.getProcessDefinitionId();
        //获取流程模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        String myActivityId = null;
        //查询已经完成的流程节点，查询到要退回到的目标节点，则跳出循环
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
                .executionId(myTask.getExecutionId()).finished().list();
        for (HistoricActivityInstance hai : haiList) {
            if (myTask.getId().equals(hai.getTaskId())) {
                myActivityId = hai.getActivityId();
                break;
            }
        }
        FlowNode myFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(myActivityId);
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        //记录原活动方向
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(flowNode.getOutgoingFlows());
        //清理活动方向
        flowNode.getOutgoingFlows().clear();
        //建立新方向
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newSequenceFlowId");
        newSequenceFlow.setSourceFlowElement(flowNode);
        newSequenceFlow.setTargetFlowElement(myFlowNode);
        newSequenceFlowList.add(newSequenceFlow);
        flowNode.setOutgoingFlows(newSequenceFlowList);
//        Authentication.setAuthenticatedUserId(nowUser);
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "退回");
        //完成任务
        taskService.complete(task.getId());
        //恢复原方向
        flowNode.setOutgoingFlows(oriSequenceFlows);
        log.info("退回成功！");
    }

}
