package cn.zq.dao;

import cn.zq.pojo.MaterialRequirementOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-06-12
 */
public interface MaterialRequirementOutputMapper extends BaseMapper<MaterialRequirementOutput> {
    List getList();
}
