package cn.zq.service.activiti.serviceTask;

import cn.zq.pojo.SaleOrder;
import cn.zq.service.SaleOrderService;
import cn.zq.service.UserService;
import cn.zq.service.impl.SaleOrderServiceImpl;
import cn.zq.service.impl.UserServiceImpl;
import cn.zq.utils.BeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.HashMap;

public class OutputRequirementServiceTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String id = delegateExecution.getId();
        String parentId = delegateExecution.getParentId();
        UserService userService = (UserService) BeanUtils.getBean(UserService.class);
        System.out.println("this is output serviceTask!");
        System.out.println(userService.findByUsername("kjk"));
        System.out.println("id:"+id);
        System.out.println("parentId:"+parentId);
    }
}
