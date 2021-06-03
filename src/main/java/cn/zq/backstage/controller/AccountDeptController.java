package cn.zq.backstage.controller;


import cn.zq.backstage.domain.AccountDept;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.service.AccountDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/account/dept")
public class AccountDeptController {
    @Autowired
    AccountDeptService accountDeptService;
    @RequestMapping("/getList")
    public Message getList(){
        Message<List> accountDeptMessage = new Message<>();
        QueryWrapper<AccountDept> queryWrapper = new QueryWrapper();
        accountDeptMessage.setData((List)accountDeptService.list());
        accountDeptMessage.setCode("200");
        accountDeptMessage.setInfo("测试mp接口");
        return accountDeptMessage;
    }
}

