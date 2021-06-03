package cn.zq.backstage.controller;


import cn.zq.backstage.domain.Test2;
import cn.zq.backstage.service.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/test2")
public class Test2Controller {
    @Autowired
    Test2Service test2Service;
    @RequestMapping("/test2Main")
    public Test2 main(@RequestParam Integer id){
        return test2Service.getById(id);
    }
}

