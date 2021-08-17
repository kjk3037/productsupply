package cn.zq.backstage.dao;

import cn.zq.backstage.domain.WorkflewEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
@Mapper
public interface WorkflewEntityMapper extends BaseMapper<WorkflewEntity> {
    WorkflewEntity selectByEid(Integer id);
}
