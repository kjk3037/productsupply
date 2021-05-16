package cn.zq.backstage.controller;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.SysModule;
import cn.zq.backstage.service.SysModuleService;
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
