package cn.zq.backstage.dao;

import cn.zq.backstage.domain.WorkflewEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2021-09-03
 */
public interface WorkflewEntityMapper extends BaseMapper<WorkflewEntity> {
    WorkflewEntity selectByEid(String id);
}