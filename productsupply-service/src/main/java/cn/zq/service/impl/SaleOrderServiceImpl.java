package cn.zq.service.impl;

import cn.zq.dao.SaleOrderDetailMapper;
import cn.zq.pojo.*;
import cn.zq.dao.SaleOrderMapper;
import cn.zq.service.*;
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

import java.util.*;

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
    private static final String KEY="code";
    private static final String[] FIELD={"订单流码","下单日期","客户名称","业务员"};
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
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AttachmentService attachmentService;
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
        order.setReceiptStatus(0);
        order.setDeliveryStatus(0);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        List<SaleOrderDetail> saleOrderDetails = order.getSaleOrderDetails();
        List<SaleOrderDetail> newList=new ArrayList<>();
        for (SaleOrderDetail details:saleOrderDetails){
            details.setOrderCode(order.getCode());
            details.setOrderId(id);
            newList.add(details);
        }

        //设置待办信息头  待完善
        Map name=actProcessService.setFieldNameByArray(FIELD);
        Customer byId = customerService.getById(order.getCustomerId());
        User byUserId = userService.getByUsername(ShiroUtils.getUsername());
        Map value = actProcessService.setFieldValue(order.getCode(),order.getOrderDate(),byId.getName(),byUserId.getNickname());
        Map map=new HashMap();
        map.putAll(name);
        map.putAll(value);
        actProcessService.startProcess("saleOrder",order.getCode(),map);
        saleOrderMapper.insertSaleOrder(order);
        saleOrderDetailService.saveBatch(newList);
        return order.getCode();
    }
    /*
    *@describe 确认订单(审批通过)
    *@param String:订单流码；String:审批意见
    **/
    @Transactional
    public void confirmOrder(String orderCode,String comment){
        Task taskByBusKey = actTaskService.getTaskByBusKey(orderCode);

//        if (taskByBusKey.getTaskDefinitionKey().equals("sid-ea74e1f9-738b-4a42-93e5-052844c63df7")){
//            HashMap<String, Object> map = new HashMap<>();
//            QueryWrapper<SaleOrderDetail> saleOrderDetailQueryWrapper = new QueryWrapper<>();
//            saleOrderDetailQueryWrapper.eq("order_code",orderCode);
//            List<SaleOrderDetail> saleOrderDetails = saleOrderDetailMapper.selectList(saleOrderDetailQueryWrapper);
//            map.put("",saleOrderDetails);
//            for (SaleOrderDetail saleOrderDetail:saleOrderDetails){
//
//            }
//
//            actTaskService.execute(taskByBusKey);
//        }else {
//            actTaskService.execute(taskByBusKey.getProcessInstanceId(),comment);
//        }
        actTaskService.claimTask(orderCode,userService.getByUsername(ShiroUtils.getUsername()).getId());
        actTaskService.execute(taskByBusKey.getProcessInstanceId(),comment);
        finishOrder(orderCode,comment,1);
    }

    /*
    *@describe 取消订单(审批拒绝/申请人作废)
    *@param String:订单流码；String:审批意见
    **/
    @Transactional
    public void cancelOrder(String orderCode,String commment){
        finishOrder(orderCode,commment,4);
    }
    /*
    *@describe 结束订单流程(流程结束、修改订单状态)
    *@param String:订单流码；String:审批意见；Integer:订单状态
    **/
    @Transactional
    public void finishOrder(String orderCode,String comment,Integer status){
        Task task = actTaskService.getTaskByBusKey(orderCode);
        //actTaskService.execute(task.getProcessInstanceId(),comment);
        updateOrderStatus(orderCode,status);
    }
    /*
    *@describe 修改订单状态
    *@param String:订单流码；Integer:订单状态
    **/
    @Transactional
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
    @Transactional
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
    public SaleOrder getByKey(String key) {
        SaleOrder saleOrder = saleOrderMapper.selectByCode(key);
        return saleOrder;
    }


}
