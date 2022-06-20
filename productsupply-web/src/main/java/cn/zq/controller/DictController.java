package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.Dict;
import cn.zq.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-06-16
 */
@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    DictService dictService;
    @GetMapping("/getAllByType")
    public Message getAllByType(String type){
        return Message.success(dictService.getByType(type));
    }
}

