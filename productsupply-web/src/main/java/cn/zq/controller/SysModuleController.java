package cn.zq.controller;

import cn.zq.domain.Message;
import cn.zq.domain.SysModule;
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
        Message<List> listMessage = new Message<List>();
        listMessage.setData(sysModuleService.getModule());
        return listMessage;
    }
}
