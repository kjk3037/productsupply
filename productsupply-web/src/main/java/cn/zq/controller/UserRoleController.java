package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/role")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @GetMapping("/getList")
    public Message getList(){
        return Message.success(userRoleService.getRoles());
    }
}
