package cn.zq.service.activiti.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class InputRequirementServiceTask implements JavaDelegate {
    @Autowired

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String id = delegateExecution.getId();
        String parentId = delegateExecution.getParentId();
        System.out.println("this is input serviceTask!");
        System.out.println("id:"+id);
        System.out.println("parentId:"+parentId);
    }
}
