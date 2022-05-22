package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.SaleOrder;
import cn.zq.service.SaleOrderService;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/sale/order")
public class SaleOrderController {
    @Autowired
    SaleOrderService saleOrderService;
    @Autowired
    ActTaskService actTaskService;
    @Autowired
    ActProcessService actProcessService;
    @GetMapping("/getByKey")
    public Message getByKey(String key){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",saleOrderService.getByKey(key));
        map.put("process",actProcessService.getHisActivitiesByBusinessKey(key));
        return Message.success(map);
    }
    @PostMapping("/createOrder")
    public Message createOrder(@RequestBody SaleOrder order){
        String resultCode = saleOrderService.createOrder(order);
        return Message.success(resultCode,"下单成功");
    }
    @PostMapping("/confirm")
    public Message copnfirm(String orderCode,String comment){
        saleOrderService.confirmOrder(orderCode,comment);
        return Message.success("通过");
    }
    @GetMapping("/getList")
    public Message getList(){
        List<SaleOrder> all = saleOrderService.getAll();
        return Message.success(all);
    }
}

