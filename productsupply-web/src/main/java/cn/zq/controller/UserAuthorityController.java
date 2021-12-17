package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.domain.UserAuthority;
import cn.zq.service.UserAuthorityService;
import com.alibaba.druid.sql.visitor.functions.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/authority")
public class UserAuthorityController {
    @Autowired
    UserAuthorityService userAuthorityService;
    @GetMapping("/getList")
    public Message getList(){
        return Message.success(userAuthorityService.getList());
    }
    @PostMapping("/selectByPrimaryKey")
    public Message selectByPrimaryKey(@RequestBody Integer id){
        UserAuthority userAuthority = userAuthorityService.selectByPrimaryKey(id);
        if (!userAuthority.equals(null)) {
            return Message.success(userAuthority);
        }else {
            return Message.failed("找不到相关权限!");
        }

    }
}
