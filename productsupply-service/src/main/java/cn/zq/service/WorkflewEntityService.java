package cn.zq.service;

import cn.zq.domain.WorkflewEntity;
import cn.zq.domain.WorkflewNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2021-09-03
 */
public interface WorkflewEntityService extends IService<WorkflewEntity> {
    List<WorkflewNode> getLink(Integer eid);
    int addNewEntity(String uid,Integer moduleEntityId,Integer workflewId);
    int addNextEntity(Integer wId,Integer nId,String eId);
}
