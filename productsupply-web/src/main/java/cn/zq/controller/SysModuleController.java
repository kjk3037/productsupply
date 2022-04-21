package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/module")
@RestController
@CrossOrigin
public class SysModuleController {
    @Autowired
    SysModuleService sysModuleService;
    @GetMapping("/getList")
    public Message getList(){
        return Message.success(sysModuleService.getModule());
    }
}
