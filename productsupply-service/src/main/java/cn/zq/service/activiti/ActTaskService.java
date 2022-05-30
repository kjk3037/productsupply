package cn.zq.service.activiti;

import cn.zq.dao.SysSubModuleMapper;
import cn.zq.pojo.SysSubModule;
import cn.zq.service.UserService;
import cn.zq.utils.ShiroUtils;
import cn.zq.vo.TodoVo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    SysSubModuleMapper subModuleMapper;
    @Autowired
    UserService userService;
    /*
     *
     * */
    /*
    *@describe 执行操作节点(无流程参数)
    *@param 流程实例Id
    **/
    public void execute(String instId,String comment){
        //System.out.println("执行"+instId+"节点");
        execute(instId, new HashMap(), comment);
    }

    /*
    *@describe 执行任务节点(含流程参数且不允许是并行流程)
    *@param 流程实例Id,流程变量
    **/
    public void execute(String instId, Map<String,Object> args,String comment){
        Task task = taskService.createTaskQuery()
                .processInstanceId(instId)
                .singleResult();
        if (task!=null){
            if (!comment.isEmpty()&&!comment.equals("")&&comment!=null){
                taskService.addComment(task.getId(),instId,comment);
            }
            if (args.isEmpty()){
                taskService.complete(task.getId());
            }else{
                taskService.complete(task.getId(),args);
            }
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
        if (tasks==null||tasks.isEmpty()){
            log.info("not find any task by username:"+username);
        }
        return tasks;

    }
    /*
    *@describe 根据实例ID获取当前任务信息(确定单一流程节点)
    *@param 流程实例Id;
    **/
    public Task getTaskById(String id){
        Task task = taskService.createTaskQuery()
                .processInstanceId(id)
                .singleResult();
        if (task!=null){
            return task;
        }
        log.info("not find task by taskId:" +id);
        return task;
    }
    /*
    *@describe 根据bussinessKey获取任务信息
    *@param String bussinessKey
    **/
    public Task getTaskByBusKey(String key){
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(key).singleResult();
        if (task!=null){
//            System.out.println("task:"+task.toString());
//            System.out.println("taskName:"+task.getName());
//            System.out.println("taskAssignee:"+task.getAssignee());
            return task;
        }
        log.info("not find task by businessKey:" +key);
        return task;
    }
    /*
    * 根据业务key增加代办人员
    * */
    public void addCandidateUserByBusinessKey(String businessKey,String username){
        Task task = getTaskByBusKey(businessKey);
        taskService.addCandidateUser(task.getId(),username);
    }
    /*
    * 根据业务key领取任务
    * */
    public void claimTask(String businessKey,String username){
        Task taskByBusKey = getTaskByBusKey(businessKey);
        taskService.claim(taskByBusKey.getId(),username);
    }
    /*
    * 根据用户账号获取待办任务信息
    * */
    public List getAllTodoList(){
        TaskQuery taskQuery = taskService.createTaskQuery();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<Task> tasks = taskQuery.taskCandidateUser(userService.getByUsername(ShiroUtils.getUsername()).getId()).list();
        List<TodoVo> todoList = new ArrayList<>();
        for(Task task:tasks){
            TodoVo todoVo = new TodoVo();
            ProcessInstance processInstance = processInstanceQuery.processInstanceId(task.getProcessInstanceId()).singleResult();
            Map<String, Object> variables = taskService.getVariables(task.getId());
            SysSubModule sysSubModule = subModuleMapper.selectByName(processInstance.getProcessDefinitionName());
            todoVo.setProcessName(processInstance.getProcessDefinitionName());
            todoVo.setActivityName(task.getName());
            todoVo.setInfo(variables);
            todoVo.setPath(sysSubModule.getParentPath()+sysSubModule.getPath());
            todoVo.setProcessDefKey(processInstance.getProcessDefinitionKey());
            todoVo.setBussinessKey(processInstance.getBusinessKey());
            todoList.add(todoVo);
        }
        return todoList;
    }
    /*
    *@describe 节点退回
    *@param String:流程实例Id;String:当前任务Id；String:目标任务Id
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
        newSequenceFlow.setId(UUID.randomUUID().toString());
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
    /*
    *@describe 撤回节点
    *@param String:流程实例Id;String:当前任务Id；String:目标任务Id
    **/
    public void revoke(String processInstanceId, String currentTaskId,String targetTaskId) throws Exception {
        Task task = taskService.createTaskQuery().taskId(targetTaskId).singleResult();
        if(!task.getAssignee().equals(ShiroUtils.getUsername())){
            throw new Exception("该任务节点不属于此用户！");
        }else {
            rollback(processInstanceId,currentTaskId,targetTaskId);
        }
    }
    /*
    * 关闭流程
    * */
    public void endTask(String taskId) {
        //  当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        List endEventList = bpmnModel.getMainProcess().findFlowElementsOfType(EndEvent.class);
        FlowNode endFlowNode = (FlowNode) endEventList.get(0);
        FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(task.getTaskDefinitionKey());

        //  临时保存当前活动的原始方向
        List originalSequenceFlowList = new ArrayList<>();
        originalSequenceFlowList.addAll(currentFlowNode.getOutgoingFlows());
        //  清理活动方向
        currentFlowNode.getOutgoingFlows().clear();

        //  建立新方向
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newSequenceFlowId");
        newSequenceFlow.setSourceFlowElement(currentFlowNode);
        newSequenceFlow.setTargetFlowElement(endFlowNode);
        List newSequenceFlowList = new ArrayList<>();
        newSequenceFlowList.add(newSequenceFlow);
        //  当前节点指向新的方向
        currentFlowNode.setOutgoingFlows(newSequenceFlowList);

        //  完成当前任务
        taskService.complete(task.getId());

        //  可以不用恢复原始方向，不影响其它的流程
        currentFlowNode.setOutgoingFlows(originalSequenceFlowList);
    }

}
