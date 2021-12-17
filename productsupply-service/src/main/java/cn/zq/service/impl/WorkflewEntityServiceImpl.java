package cn.zq.service.impl;

import cn.zq.dao.WorkflewExecutorMapper;
import cn.zq.dao.WorkflewMapper;
import cn.zq.dao.WorkflewNodeMapper;
import cn.zq.domain.Workflew;
import cn.zq.domain.WorkflewEntity;
import cn.zq.dao.WorkflewEntityMapper;
import cn.zq.domain.WorkflewNode;
import cn.zq.service.WorkflewEntityService;
import cn.zq.service.WorkflewNodeService;
import cn.zq.utils.FormatUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2021-09-03
 */
@Service
public class WorkflewEntityServiceImpl extends ServiceImpl<WorkflewEntityMapper, WorkflewEntity> implements WorkflewEntityService {
    @Autowired
    WorkflewNodeMapper workflewNodeMapper;
    @Autowired
    WorkflewEntityMapper workflewEntityMapper;
    @Autowired
    WorkflewNodeService workflewNodeService;
    @Autowired
    WorkflewExecutorMapper workflewExecutorMapper;
    @Autowired
    WorkflewMapper workflewMapper;
    @Override
    public List<WorkflewNode> getLink(Integer eid) {
        //获取数据所属流程所有节点
        WorkflewEntity workflewEntity = workflewEntityMapper.selectByEid(eid.toString());
        return workflewNodeService.getLink(workflewEntity.getWorkflewId());
    }
    /* 新增数据流程实例 */
    @Transactional
    @Override
    public int addNewEntity(String uid,Integer moduleEntityId,Integer workflewId) {
        WorkflewEntity workflewEntity = new WorkflewEntity();
        //初始化流程实例信息头节点
        workflewEntity.setId(FormatUtils.uuidFormat());
        workflewEntity.setSubModuleId(moduleEntityId);
        workflewEntity.setWorkflewId(workflewId);
        workflewEntity.setStatus(2);
        //workflewEntity.setApproverId(uid);
        WorkflewNode firstNode = workflewNodeService.selectFirstNode(workflewId);
        workflewEntity.setWorkflewNodeId(firstNode.getId());
        //流程对象insert到数据库
        workflewEntityMapper.insert(workflewEntity);
        //新增next节点
        workflewEntity.setWorkflewNodeId(firstNode.getNextNodeId());
        //待补入操作人
        workflewEntityMapper.insert(workflewEntity);
        return 1;
    }

    /*  生成下一个节点 */
    @Transactional
    public int addNextEntity(Integer wId,Integer nId,String eid){
        WorkflewNode nextNode = workflewNodeMapper.getNextNodeById(nId);
        if (nextNode.equals(null)){
            return 0;
        }
        //填充节点数据
        WorkflewEntity workflewEntity = new WorkflewEntity();
        workflewEntity.setSubModuleId(workflewMapper.selectById(nextNode.getWorkflewId()).getSubModuleId());
        //如果此节点是填写或者审批节点，节点初始状态为进行中(1)，其他为已结束
        if (nextNode.getNodeType()==2 || nextNode.getNodeType()==1){
            workflewEntity.setStatus(1);
        }else {
            workflewEntity.setStatus(2);
        }
        workflewEntity.setPrevEntityId(eid);
        workflewEntity.setId(FormatUtils.uuidFormat());
        workflewEntity.setWorkflewNodeId(nextNode.getId());
        workflewEntity.setCreateTime(new Date());
        workflewEntity.setUpdateTime(new Date());
        workflewEntity.setExecutorId("");
        return workflewEntityMapper.insert(workflewEntity);
    }
}
