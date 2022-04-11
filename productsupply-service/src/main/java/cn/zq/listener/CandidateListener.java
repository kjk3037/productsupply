package cn.zq.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.task.IdentityLink;

import java.util.ArrayList;
import java.util.Set;

/*
* 动态注入任务节点的处理人
* */
public class CandidateListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("进入监听器");
        Set<IdentityLink> candidates = delegateTask.getCandidates();
        System.out.println(candidates.size());
        if (!candidates.isEmpty() &&candidates!=null){
            for (IdentityLink identityLink: candidates){
                System.out.println(identityLink);
            }
        }
    }
}
