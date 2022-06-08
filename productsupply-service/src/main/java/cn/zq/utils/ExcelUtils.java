package cn.zq.utils;

import cn.zq.pojo.BillOfMaterial;
import cn.zq.service.BillOfMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.util.List;

public class ExcelUtils {
    public static void outputBOM(String parentCode){
        BillOfMaterialService bomService = (BillOfMaterialService) BeanUtils.getBean(BillOfMaterialService.class);
        List completeBOM = bomService.getCompleteBOM(parentCode,"0");


    }
}
