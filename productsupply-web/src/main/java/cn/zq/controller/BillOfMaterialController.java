package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.service.BillOfMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-06-08
 */
@RestController
@RequestMapping("/billOfMaterial")
public class BillOfMaterialController {
    @Autowired
    BillOfMaterialService billOfMaterialService;
    @GetMapping("/test")
    public Message test(String code){
        return Message.success(billOfMaterialService.getCompleteBOM(code,"0"));
    }
}

