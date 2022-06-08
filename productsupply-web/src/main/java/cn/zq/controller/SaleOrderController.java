package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.Attachment;
import cn.zq.pojo.FieldStatus;
import cn.zq.pojo.SaleOrder;
import cn.zq.service.AttachmentService;
import cn.zq.service.FieldStatusService;
import cn.zq.service.SaleOrderService;
import cn.zq.service.activiti.ActProcessService;
import cn.zq.service.activiti.ActTaskService;
import cn.zq.vo.ActivityVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    FieldStatusService fieldStatusService;
    @Autowired
    AttachmentService attachmentService;
    @GetMapping("/getByKey")
    public Message getByKey(String key) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String taskDefinitionKey = actTaskService.getTaskByBusKey(key).getTaskDefinitionKey();
        SaleOrder saleOrder = saleOrderService.getByKey(key);
        QueryWrapper<Attachment> attachmentQueryWrapper = new QueryWrapper<>();
        attachmentQueryWrapper.eq("business","saleOrder").eq("business_field","attachment").eq("business_key",saleOrder.getCode());
        saleOrder.setAttachments(attachmentService.list(attachmentQueryWrapper));
        map.put("data",saleOrder);
        map.put("process",actProcessService.getHisActivitiesByBusinessKey(key));
        map.put("field",fieldStatusService.getMapByKey("saleOrder", taskDefinitionKey));
        return Message.success(map);
    }
    @PostMapping("/createOrder")
    public Message createOrder(@RequestParam SaleOrder order,@RequestParam Map<String, List<MultipartFile>> files){
        String resultCode = saleOrderService.createOrder(order);
        attachmentService.uploads(files,"saleOrder",resultCode);
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

