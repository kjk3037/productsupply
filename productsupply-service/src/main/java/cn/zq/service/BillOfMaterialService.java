package cn.zq.service;

import cn.zq.pojo.BillOfMaterial;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-06-08
 */
public interface BillOfMaterialService extends IService<BillOfMaterial> {
    List getBOM(String parentCode);
    List getCompleteBOM(String parentCode,String parentLevel);
    List getListBOMDetail(List codes);
}
