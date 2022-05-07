package cn.zq.dao;

import cn.zq.domain.DataField;
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
}
