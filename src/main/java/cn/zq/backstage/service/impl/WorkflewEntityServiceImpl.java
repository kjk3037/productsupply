package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.WorkflewNodeMapper;
import cn.zq.backstage.domain.Workflew;
import cn.zq.backstage.domain.WorkflewEntity;
import cn.zq.backstage.dao.WorkflewEntityMapper;
import cn.zq.backstage.domain.WorkflewNode;
import cn.zq.backstage.service.WorkflewEntityService;
import cn.zq.backstage.service.WorkflewNodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class WorkflewEntityServiceImpl extends ServiceImpl<WorkflewEntityMapper, WorkflewEntity> implements WorkflewEntityService {
    @Autowired
    WorkflewNodeMapper workflewNodeMapper;
    @Autowired
    WorkflewEntityMapper workflewEntityMapper;
    @Autowired
    WorkflewNodeService workflewNodeService;
    /* 获取能获取的所有节点 */
    @Override
    public List<WorkflewNode> getLink(Integer eid) {
        //获取数据所属流程所有节点
        WorkflewEntity workflewEntity = workflewEntityMapper.selectByEid(eid);
        List<WorkflewNode> nodeList=workflewNodeMapper.getLinkById(workflewEntity.getWorkflewId());
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
    /* 新增数据流程实例 */
    @Override
    public int addNewEntity(Integer moduleEntityId,Integer workflewId) {
        WorkflewEntity workflewEntity = new WorkflewEntity();
        workflewEntity.setModuleEntityId(moduleEntityId);
        workflewEntity.setWorkflewId(workflewId);
        workflewEntity.setStatus(2);
        WorkflewNode firstNode = workflewNodeService.selectFirstNode(workflewId);
        workflewEntity.setWorkflewNodeId(firstNode.getId());
        workflewEntityMapper.insert(workflewEntity);
        workflewEntity.setWorkflewNodeId(firstNode.getNextNodeId());
        //待补入操作人
        workflewEntityMapper.insert(workflewEntity);
        return 1;
    }

    private LinkedList<WorkflewNode> sortLink(List<WorkflewNode> nodeList,LinkedList<WorkflewNode> link) {
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
