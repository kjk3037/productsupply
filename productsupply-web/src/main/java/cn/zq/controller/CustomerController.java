package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.Customer;
import cn.zq.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/getAll")
    public Message getAll(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("","");
        List<Customer> list = customerService.list();
        return Message.success(list);
    }
}

