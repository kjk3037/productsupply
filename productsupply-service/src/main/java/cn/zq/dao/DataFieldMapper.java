package cn.zq.dao;

import cn.zq.pojo.DataField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-04-19
 */
public interface DataFieldMapper extends BaseMapper<DataField> {
    List getByBussinessKey(String bussinessKey);
    List getByBussinessKeyAll(String bussinessKey);
    List selectChild(String pid);
}
