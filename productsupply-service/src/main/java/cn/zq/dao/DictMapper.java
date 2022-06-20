package cn.zq.dao;

import cn.zq.pojo.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-06-16
 */
public interface DictMapper extends BaseMapper<Dict> {
    List<Dict> selectByType(String typeName);
}
