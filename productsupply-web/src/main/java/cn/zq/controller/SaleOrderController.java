package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.SaleOrder;
import cn.zq.service.SaleOrderService;
import cn.zq.service.activiti.ActTaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/createOrder")
    public Message createOrder(@RequestBody SaleOrder order){
        String resultCode = saleOrderService.createOrder(order);
        return Message.success(resultCode,"下单成功");
    }
    @PostMapping("/agree")
    public Message agree(String orderCode,String comment){
        saleOrderService.agree(orderCode,comment);
        return Message.success("通过");
    }
    @GetMapping("/getList")
    public Message getList(){
        List<SaleOrder> all = saleOrderService.getAll();
        return Message.success(all);
    }
}

