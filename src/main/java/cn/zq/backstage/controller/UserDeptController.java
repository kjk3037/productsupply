package cn.zq.backstage.controller;


import cn.zq.backstage.domain.UserDept;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.service.UserDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/user/dept")
public class UserDeptController {
    @Autowired
    UserDeptService userDeptService;
    @RequestMapping("/getList")
    public Message getList(){
        Message<List> userDeptMessage = new Message<>();
        //QueryWrapper<UserDept> queryWrapper = new QueryWrapper();
        userDeptMessage.setData(userDeptService.getList());
        userDeptMessage.setCode("200");
        userDeptMessage.setInfo("测试mp接口");
        return userDeptMessage;
    }
}

