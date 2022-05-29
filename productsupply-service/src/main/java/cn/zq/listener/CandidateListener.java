package cn.zq.listener;

import cn.zq.pojo.User;
import cn.zq.pojo.UserRole;
import cn.zq.service.UserRoleService;
import cn.zq.service.UserService;
import cn.zq.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.task.IdentityLink;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
* 动态注入任务节点的处理人
* */
@Slf4j
public class CandidateListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("调用代办人设置监听器");
        Set<IdentityLink> candidates = delegateTask.getCandidates();

        if (!candidates.isEmpty() &&candidates!=null){
            for (IdentityLink identityLink: candidates){
                String[] s = identityLink.getUserId().split(":");
                //根据角色设置待办人员
                if (s[0].equals("role")){
                    UserRoleService userRoleService = (UserRoleService) BeanUtils.getBean(UserRoleService.class);
                    UserRole role = userRoleService.getByName(s[1]);
                    List<User> users = role.getUsers();
                    for (User user:users){
                        delegateTask.addCandidateUser(user.getId());
                    }
                    //根据用户账号设置待办人员
                }else if(s[0].equals("user")){
                    UserService userService= (UserService) BeanUtils.getBean(UserService.class);
                    User user = userService.getByUsername(s[1]);
                    delegateTask.addCandidateUser(user.getId());
                }
                //根据部门设置待办人员  -->  待完善
                delegateTask.deleteCandidateUser(identityLink.getUserId());
                delegateTask.deleteUserIdentityLink(identityLink.getUserId(),"participant");
            }
        }

    }
}
