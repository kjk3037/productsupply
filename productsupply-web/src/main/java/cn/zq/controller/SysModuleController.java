package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/module")
@RestController
public class SysModuleController {
    @Autowired
    SysModuleService sysModuleService;
    @GetMapping("/getList")
    public Message getList(){
        return Message.success(sysModuleService.getModule());
    }
}
