package cn.zq.controller;

import cn.zq.domain.Message;
import cn.zq.domain.UserAccount;
import cn.zq.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account/user")
@RestController
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;
    @PostMapping("/login")
    public Message login(@RequestBody UserAccount userAccount){
        return userAccountService.login(userAccount);
    }
    @GetMapping("/getList")
    public Message getUsers(){
        return userAccountService.getUsers();
    }
    @PostMapping("/add")
    public Message add(@RequestBody UserAccount userAccount){
        return userAccountService.addUser(userAccount);
    }
}
