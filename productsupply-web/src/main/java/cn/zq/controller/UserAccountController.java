package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.domain.UserAccount;
import cn.zq.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/account")
@RestController
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;
    @PostMapping("/login")
    public Message login(@RequestBody UserAccount userAccount){
        UserAccount account=userAccountService.login(userAccount);
        if(!account.equals(null)){
            return Message.success(account,"登录成功!");
        }else{
            return Message.failed("账号或者密码错误,登录失败!");
        }

    }
    @GetMapping("/getList")
    public Message getUsers(){
        return Message.success(userAccountService.getUsers());
    }
    @PostMapping("/add")
    public Message add(@RequestBody UserAccount userAccount){
        int count = userAccountService.addUser(userAccount);
        if (count>0){
            return Message.success(count,"注册成功!");
        }else {
            return Message.failed("发生某种错误，注册失败!");
        }

    }
}
