package cn.zq.service.impl;

import cn.zq.dao.SaleOrderDetailMapper;
import cn.zq.pojo.SaleOrder;
import cn.zq.dao.SaleOrderMapper;
import cn.zq.service.SaleOrderDetailService;
import cn.zq.service.SaleOrderService;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import cn.zq.utils.FormatUtils;
import cn.zq.utils.ShiroUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {
    @Autowired
    SaleOrderMapper saleOrderMapper;
    @Autowired
    SaleOrderDetailMapper saleOrderDetailMapper;
    @Autowired
    SaleOrderDetailService saleOrderDetailService;
    @Autowired
    ActProcessService actProcessService;
    @Autowired
    ActTaskService actTaskService;
    /*
    *@describe 新增销售订单流程
    *@param SaleOrder:订单内容实例
    **/
    @Transactional
    public String createOrder(SaleOrder order){
        Integer id=1;
        if (saleOrderMapper.maxId()!=null){
            id=saleOrderMapper.maxId()+1;
        }
        order.setCode(FormatUtils.codeFormat("sale",id));
        order.setOrderStatus(3);
        order.setReceiptStatus(1);
        order.setDeliveryStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        //设置待办信息头  待完善
        Map map = actProcessService.setFieldData(order.getCode(),order.getOrderDate(),order.getCustomerId(),order.getUserId());
        actProcessService.startProcess("saleOrder",order.getCode(),map);
        saleOrderMapper.insertSaleOrder(order);
        saleOrderDetailService.saveBatch(order.getSaleOrderDetails());
        return order.getCode();
    }
    /*
    *@describe 确认订单(审批通过)
    *@param String:订单流码；String:审批意见
    **/
    public void confirmOrder(String orderCode,String comment){
        finishOrder(orderCode,comment,1);
    }

    /*
    *@describe 取消订单(审批拒绝/申请人作废)
    *@param String:订单流码；String:审批意见
    **/
    public void cancelOrder(String orderCode,String commment){
        finishOrder(orderCode,commment,4);
    }
    /*
    *@describe 结束订单流程(流程结束、修改订单状态)
    *@param String:订单流码；String:审批意见；Integer:订单状态
    **/
    public void finishOrder(String orderCode,String comment,Integer status){
        Task task = actTaskService.getTaskByBusKey(orderCode);
        if (!task.getAssignee().equals(SecurityUtils.getSubject().getPrincipal())){
            return;
            //具体处理待完善
        }
        actTaskService.execute(task.getProcessInstanceId(),comment);
        updateOrderStatus(orderCode,status);
    }
    /*
    *@describe 修改订单状态
    *@param String:订单流码；Integer:订单状态
    **/
    public Integer updateOrderStatus(String orderCode,Integer status){
        QueryWrapper<SaleOrder> wrapper = new QueryWrapper();
        wrapper.eq("code",orderCode);
        SaleOrder saleOrder = saleOrderMapper.selectByCode(orderCode);
        saleOrder.setOrderStatus(status);
        return saleOrderMapper.update(saleOrder,wrapper);
    }
    /*
    *@describe 修改订单
    *@param SaleOrder:订单内容
    **/
    public Integer updateOrder(SaleOrder saleOrder){
        QueryWrapper<SaleOrder> wrapper = new QueryWrapper();
        wrapper.eq("code",saleOrder.getCode());
        return saleOrderMapper.update(saleOrder,wrapper);
    }

    @Override
    public List<SaleOrder> getAll() {
        return saleOrderMapper.selectAll();
    }

    @Override
    public void agree(String orderCode, String comment) {
        Task taskByBusKey = actTaskService.getTaskByBusKey(orderCode);
        actTaskService.claimTask(orderCode,ShiroUtils.getUsername());
        actTaskService.execute(taskByBusKey.getProcessInstanceId(),comment);
    }

}
