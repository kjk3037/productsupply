package cn.zq.backstage.controller;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.User;
import cn.zq.backstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Message login(@RequestBody User user){
        return userService.login(user);
    }
    @GetMapping("/getList")
    public Message getUsers(){
        return userService.getUsers();
    }
}
