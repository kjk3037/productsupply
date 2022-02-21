package cn.zq.controller;


import cn.zq.common.Message;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/user/permission")
public class UserPermissionController {
    @RequestMapping("/unautho")
    public Message unautho(){
        return Message.failed("没有权限访问该资源!");
    }
}

