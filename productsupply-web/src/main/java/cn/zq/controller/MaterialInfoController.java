package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.service.MaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/materialInfo")
public class MaterialInfoController {
    @Autowired
    MaterialInfoService materialInfoService;
    @GetMapping("/getAll")
    public Message getAll(){
        return Message.success(materialInfoService.getAllMaterial(),"获取成功");
    }
    @GetMapping("/getByCode")
    public Message getByCode(@RequestParam String code){
        return Message.success(materialInfoService.getByCode(code),"获取成功");
    }

}

