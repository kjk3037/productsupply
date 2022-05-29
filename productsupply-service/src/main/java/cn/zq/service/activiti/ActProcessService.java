package cn.zq.service.activiti;

import cn.zq.pojo.User;
import cn.zq.service.UserService;
import cn.zq.utils.BeanUtils;
import cn.zq.utils.ShiroUtils;
import cn.zq.vo.ActivityVo;
import cn.zq.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

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
    @Autowired
    UserService userService;
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
    public void startProcess(String processKey,String businessKey,Map args){
        UserService userService = (UserService) BeanUtils.getBean(UserService.class);
        User user = userService.getByUsername(ShiroUtils.getUsername());
        args.put("businessKey",businessKey);
        args.put("user",user.getId());
        //args.put("leader","jack");
        ProcessInstance test = runtimeService.startProcessInstanceByKey(processKey,businessKey,args);
        //执行申请节点
        taskService.execute(test.getProcessInstanceId(),"");
        log.info("ProcessInstance is created");
    }
    public List getHisActivitiesByBusinessKey(String businessKey) throws Exception {
      return getHisActivities(runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult().getProcessInstanceId()) ;
    }
    /*
    *@describe 获取流程实例历史节点且转换成ActivityVo
    *@param String:instId
    **/
    public List getHisActivities(String instId) throws Exception {
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.processInstanceId(instId).activityType("userTask").list();
        Collections.sort(list, new Comparator<HistoricActivityInstance>() {
            @Override
            public int compare(HistoricActivityInstance o1, HistoricActivityInstance o2) {
                return Integer.valueOf(o1.getId())-Integer.valueOf(o2.getId());
            }
        });
        // 转换Vo对象
        List<ActivityVo> activityVos = new ArrayList<>();
        for (HistoricActivityInstance instance:list){
            ActivityVo activityVo = new ActivityVo();
            BeanUtils.copyF(instance,activityVo);
            if (activityVo.getAssignee()!=null&&activityVo.getAssignee()!="") {
                User byUserId = userService.getByUserId(activityVo.getAssignee());
                UserVo userVo = new UserVo(byUserId);
                activityVo.setAssigneeInfo(userVo);
            }else {
                //补全节点待办人信息
                setActivityCandidate(activityVo);
            }
            activityVos.add(activityVo);
        }
        return activityVos;
    }
    /*
    * 给相应未完成的userTask节点填充待办人
    * */
    public void setActivityCandidate(ActivityVo activityVo) throws Exception {
        if (activityVo.getActivityType().equals("userTask") && activityVo.getEndTime()==null) {
            List<UserVo> candidates = new ArrayList<>();
            List<HistoricIdentityLink> historicIdentityLinksForTask = historyService.getHistoricIdentityLinksForTask(activityVo.getTaskId());
            for (HistoricIdentityLink i : historicIdentityLinksForTask) {
                User userById = userService.getByUserId(i.getUserId());
                UserVo user = new UserVo(userById);
                candidates.add(user);
            }
            activityVo.setCandidates(candidates);
        }
    }
    /*
    * 添加流程变量(属性数据)
    * */
    public Map setFieldValue(Object o1,Object o2,Object o3,Object o4){
        HashMap<String, Object> var = new HashMap<>();
        var.put("value1",o1);
        var.put("value2",o2);
        //以下参数待完善
        var.put("value3",o3);
        var.put("value4",o4);
        return var;
    }
    /*
    * 添加流程变量(属性名称)
    * */
    public Map setFieldName(Object o1,Object o2,Object o3,Object o4){
        HashMap<String, Object> var = new HashMap<>();
        var.put("field1",o1);
        var.put("field2",o2);
        //以下参数待完善
        var.put("field3",o3);
        var.put("field4",o4);
        return var;
    }
}
