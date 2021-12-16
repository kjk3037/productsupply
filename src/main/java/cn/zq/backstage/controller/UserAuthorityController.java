package cn.zq.backstage.controller;

import cn.zq.backstage.domain.UserAuthority;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.service.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/authority")
public class UserAuthorityController {
    @Autowired
    UserAuthorityService userAuthorityService;
    @GetMapping("/getList")
    public Message getList(){
        Message<List> authorityMessage = new Message<>();
        authorityMessage.setData(userAuthorityService.getList());
        authorityMessage.setInfo("读取成功");
        authorityMessage.setCode("200");
        return authorityMessage;
    }
    @PostMapping("/selectByPrimaryKey")
    public Message selectByPrimaryKey(@RequestBody Integer id){
        Message<UserAuthority> authorityMessage = new Message<>();
        authorityMessage.setData(userAuthorityService.selectByPrimaryKey(id));
        authorityMessage.setCode("200");
        authorityMessage.setInfo("读取成功");
        return authorityMessage;
    }
}
