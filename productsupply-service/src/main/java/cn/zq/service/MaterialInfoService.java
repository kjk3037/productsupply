package cn.zq.service;

import cn.zq.pojo.MaterialInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
public interface MaterialInfoService extends IService<MaterialInfo> {
    List getAllMaterial();
    MaterialInfo getByCode(String code);
    MaterialInfo getByKey(String key);
    List getListMaterial(List codes);
}
