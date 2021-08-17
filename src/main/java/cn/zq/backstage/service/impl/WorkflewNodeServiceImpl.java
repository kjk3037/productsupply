package cn.zq.backstage.service.impl;

import cn.zq.backstage.domain.Workflew;
import cn.zq.backstage.domain.WorkflewNode;
import cn.zq.backstage.dao.WorkflewNodeMapper;
import cn.zq.backstage.service.WorkflewNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
@Service
public class WorkflewNodeServiceImpl extends ServiceImpl<WorkflewNodeMapper, WorkflewNode> implements WorkflewNodeService {
    @Autowired
    WorkflewNodeMapper workflewNodeMapper;
    @Override
    public WorkflewNode selectNextNode(Integer workflewId, Integer nodeId) {
        Map conditions=new HashMap<String,Integer>();
        WorkflewNode workflewNode = selectNode(workflewId, nodeId);
        conditions.put("id",workflewNode.getNextNodeId());
        conditions.put("workflew_id",workflewId);
        WorkflewNode next= (WorkflewNode) workflewNodeMapper.selectByMap(conditions).get(0);
        return next;
    }

    @Override
    public WorkflewNode selectNode(Integer workflewId, Integer nodeId) {
        Map conditions=new HashMap<String,Integer>();
        conditions.put("workflew_id",workflewId);
        conditions.put("id",nodeId);
        WorkflewNode node= (WorkflewNode) workflewNodeMapper.selectByMap(conditions).get(0);
        return node;
    }
    @Override
    public WorkflewNode selectFirstNode(Integer workflewId) {
        Map conditions=new HashMap<String,Integer>();
        conditions.put("workflew_id",workflewId);
        conditions.put("node_type",0);
        WorkflewNode node= (WorkflewNode) workflewNodeMapper.selectByMap(conditions).get(0);
        return node;
    }
}
