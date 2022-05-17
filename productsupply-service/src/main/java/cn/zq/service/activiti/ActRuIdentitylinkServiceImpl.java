package cn.zq.service.activiti;

import cn.zq.pojo.ActRuIdentitylink;
import cn.zq.dao.ActRuIdentitylinkMapper;
import cn.zq.service.ActRuIdentitylinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-05-13
 */
@Service
public class ActRuIdentitylinkServiceImpl extends ServiceImpl<ActRuIdentitylinkMapper, ActRuIdentitylink> implements ActRuIdentitylinkService {
    @Autowired
    ActRuIdentitylinkMapper actRuIdentitylinkMapper;
    public List getByUsername(String username){
        return actRuIdentitylinkMapper.selectByUser(username);
    }
}
