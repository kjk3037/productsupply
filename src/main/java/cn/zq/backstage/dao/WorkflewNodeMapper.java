package cn.zq.backstage.dao;

import cn.zq.backstage.domain.WorkflewNode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
public interface WorkflewNodeMapper extends BaseMapper<WorkflewNode> {
    List<WorkflewNode> getLinkByWid(Integer workflew_id);
    WorkflewNode getById(Integer node_id);
    WorkflewNode getNextNodeById(Integer node_id);
}
