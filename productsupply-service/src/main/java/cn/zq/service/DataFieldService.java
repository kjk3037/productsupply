package cn.zq.service;

import cn.zq.pojo.DataField;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-04-19
 */
public interface DataFieldService extends IService<DataField> {
    List getChilds(String parentKey);
    List getByBussinessKey(String bussinessKey);
    List getByBussinessKeyAll(String bussinessKey);
}
