package cn.zq.backstage.controller;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/role")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @GetMapping("/getList")
    public Message getList(){
        return userRoleService.getRoles();
    }
}
