package cn.zq.service.activiti.serviceTask;

import cn.zq.pojo.MaterialRequirementOutput;
import cn.zq.pojo.SaleOrder;
import cn.zq.pojo.SaleOrderDetail;
import cn.zq.service.MaterialRequirementOutputService;
import cn.zq.service.SaleOrderDetailService;
import cn.zq.service.SaleOrderService;
import cn.zq.service.UserService;
import cn.zq.service.impl.SaleOrderServiceImpl;
import cn.zq.service.impl.UserServiceImpl;
import cn.zq.utils.BeanUtils;
import cn.zq.utils.ShiroUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.*;
import java.util.function.BiConsumer;

public class OutputRequirementServiceTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        UserService userService = (UserService) BeanUtils.getBean(UserService.class);
        MaterialRequirementOutputService requireOutputService = (MaterialRequirementOutputService) BeanUtils.getBean(MaterialRequirementOutputService.class);
        SaleOrderDetailService saleOrderDetailService = (SaleOrderDetailService) BeanUtils.getBean(SaleOrderDetailService.class);
        SaleOrderService saleOrderService = (SaleOrderService) BeanUtils.getBean(SaleOrderService.class);
        QueryWrapper<SaleOrderDetail> saleOrderDetailQueryWrapper = new QueryWrapper<>();
        saleOrderDetailQueryWrapper.eq("order_code",delegateExecution.getProcessInstanceBusinessKey());
        List<SaleOrderDetail> list = saleOrderDetailService.list(saleOrderDetailQueryWrapper);
        List<MaterialRequirementOutput> materialRequirementOutputs = new ArrayList<>();
        for (SaleOrderDetail saleOrderDetail:list){
            MaterialRequirementOutput materialRequirementOutput = new MaterialRequirementOutput();
            materialRequirementOutput.setBusinessKey(delegateExecution.getProcessInstanceBusinessKey());
            materialRequirementOutput.setBusinessType(1);
            materialRequirementOutput.setCount(saleOrderDetail.getCount());
            materialRequirementOutput.setCreateTime(new Date());
            materialRequirementOutput.setUpdateTime(new Date());
            materialRequirementOutput.setMaterialCode(saleOrderDetail.getMaterialCode());
            materialRequirementOutput.setRequireDate(saleOrderService.getByKey(delegateExecution.getProcessInstanceBusinessKey()).getOutRequireDate());
            materialRequirementOutput.setRequireType(1);
            materialRequirementOutput.setStatus(1);
            materialRequirementOutputs.add(materialRequirementOutput);
        }
        requireOutputService.saveBatch(materialRequirementOutputs);
        System.out.println("this is output serviceTask!");
    }
}
