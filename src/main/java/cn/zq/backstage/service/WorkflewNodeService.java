package cn.zq.backstage.service;

import cn.zq.backstage.domain.WorkflewNode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
public interface WorkflewNodeService extends IService<WorkflewNode> {
    WorkflewNode selectNextNode(Integer workflewId,Integer nodeId);
    WorkflewNode selectNode(Integer workflewId,Integer nodeId);
    WorkflewNode selectFirstNode(Integer workflewId);
}
