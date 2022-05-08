package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.domain.DataField;
import cn.zq.domain.MaterialInfo;
import cn.zq.domain.SaleOrder;
import cn.zq.domain.SaleOrderDetail;
import cn.zq.service.DataFieldService;
import cn.zq.utils.FormatUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/dataField")
public class DataFieldController {
    @Autowired
    DataFieldService dataFieldService;
    @PostMapping("/addFields")
    public Message addFields(){
        List<DataField> dataFields = FormatUtils.printAllFields(new SaleOrder(),1);
        dataFieldService.saveBatch(dataFields);
        return Message.success("OK");
    }
    @GetMapping("/getByBussinessKey")
    public Message getByBussinessKey(String key){
        List list = dataFieldService.getByBussinessKey(key);
        return Message.success(list,"字段集合获取成功");
    }
    @GetMapping("/getByBussinessKeyAll")
    public Message getByBussinessKeyAll(String key){
        List list = dataFieldService.getByBussinessKeyAll(key);
        return Message.success(list,"字段集合获取成功");
    }
    @GetMapping("/getChilds")
    public Message getChilds(String parentKey){
        return Message.success(dataFieldService.getChilds(parentKey));
    }
}

