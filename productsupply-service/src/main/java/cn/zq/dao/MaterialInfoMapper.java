package cn.zq.dao;

import cn.zq.domain.MaterialInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
public interface MaterialInfoMapper extends BaseMapper<MaterialInfo> {
    List getAll();
}
