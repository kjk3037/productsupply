package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.domain.Attachment;
import cn.zq.domain.SaleOrder;
import cn.zq.service.AttachmentService;
import cn.zq.service.SaleOrderService;
import cn.zq.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    AttachmentService attachmentService;
    @PostMapping("/createOrder")
    public Message createOrder(@RequestBody SaleOrder order){
        System.out.println(order);
        String resultCode = saleOrderService.createOrder(order);
//        if (order.getFiles()!=null) {
//            List<String> strings = FileUtils.uploadFiles(order.getFiles());
//            List<Attachment> attachments=new ArrayList<>();
//            for (String s:strings){
//                Attachment attachment = new Attachment();
//                attachment.setBussiness("saleOrder");
//                attachment.setBussinessKey(resultCode);
//                attachment.setPath(s);
//                attachments.add(attachment);
//            }
//            attachmentService.saveBatch(attachments);
//        }
        return Message.success(resultCode,"下单成功");
    }
    @GetMapping("/getList")
    public Message getList(){
        List<SaleOrder> all = saleOrderService.getAll();
        return Message.success(all);
    }
}

