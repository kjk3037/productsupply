package cn.zq.service;

import cn.zq.domain.WorkflewNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
    List<WorkflewNode> getLink(Integer wid);
}
