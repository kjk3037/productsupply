package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.pojo.MaterialRequirementOutput;
import cn.zq.service.MaterialRequirementOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/require/output")
public class MaterialRequirementOutputController {
    @Autowired
    MaterialRequirementOutputService requirementOutputService;
    @RequestMapping("/getList")
    public Message getList(){
        return Message.success(requirementOutputService.getList());
    }
}

