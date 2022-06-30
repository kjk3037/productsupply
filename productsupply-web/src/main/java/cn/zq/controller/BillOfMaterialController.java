package cn.zq.controller;


import cn.zq.common.Message;
import cn.zq.service.BillOfMaterialService;
import cn.zq.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2022-06-08
 */
@RestController
@RequestMapping("/billOfMaterial")
public class BillOfMaterialController {
    @Autowired
    BillOfMaterialService billOfMaterialService;
    @GetMapping("/test")
    public Message test(String code, HttpServletResponse response) throws Exception {
       ExcelUtils.outputBOM(code,response);
        //List completeBOM = billOfMaterialService.getCompleteBOM(code, "0");
        return Message.success("生成完毕");
    }
    @PostMapping("/getBOMListExcel")
    public Message getBOMListExcel(@RequestParam List parentCode, HttpServletResponse response) throws NoSuchMethodException, NoSuchFieldException, IOException, IllegalAccessException, InvocationTargetException {
        ExcelUtils.outputBOMList(parentCode,response);
        return Message.success("生成完毕");
    }
}

