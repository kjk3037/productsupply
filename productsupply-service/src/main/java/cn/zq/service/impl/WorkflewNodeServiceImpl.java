package cn.zq.service.impl;

import cn.zq.domain.WorkflewNode;
import cn.zq.dao.WorkflewNodeMapper;
import cn.zq.service.WorkflewNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Override
    public List<WorkflewNode> getLink(Integer wid) {
        List<WorkflewNode> nodeList=workflewNodeMapper.getLinkByWid(wid);
        //遍历查询出头节点
        WorkflewNode head=null;
        LinkedList<WorkflewNode> link=new LinkedList<>();
        Iterator<WorkflewNode> iterator=nodeList.iterator();
        while (iterator.hasNext()){
            WorkflewNode workflewNode=iterator.next();
            if (workflewNode.getNodeType()==0){
                head=workflewNode;
                iterator.remove();
            }
        }
        link.addFirst(head);
        //通过递归获取正序节点集合；
        link=sortLink(nodeList,link);
        return link;
    }
    /* 流程节点排序 */
    private LinkedList<WorkflewNode> sortLink(List<WorkflewNode> nodeList, LinkedList<WorkflewNode> link) {
        if (link.getLast().getNodeType()==5||link.getLast().getNodeType()==3) {
            return link;
        }
        Iterator<WorkflewNode> iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            WorkflewNode workflewNode = iterator.next();
            if (workflewNode.getId() == link.getLast().getNextNodeId()) {
                link.offerLast(workflewNode);
                iterator.remove();
            }
        }
        return sortLink(nodeList,link);
    }

}
