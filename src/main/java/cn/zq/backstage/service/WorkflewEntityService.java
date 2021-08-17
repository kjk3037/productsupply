package cn.zq.backstage.service;

import cn.zq.backstage.domain.WorkflewEntity;
import cn.zq.backstage.domain.WorkflewNode;
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
public interface WorkflewEntityService extends IService<WorkflewEntity> {
    List<WorkflewNode> getLink(Integer eid);
    int addNewEntity(Integer moduleEntityId,Integer workflewId);
}
