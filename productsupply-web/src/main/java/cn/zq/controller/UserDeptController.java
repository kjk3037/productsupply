package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.service.UserDeptService;
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
        return Message.success(userDeptService.getList());
    }
}

