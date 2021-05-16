package cn.zq.backstage.controller;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/getList")
    public Message getList(){
        return roleService.getRoles();
    }
}
