package cn.zq.backstage.service.impl;

import cn.zq.backstage.domain.UserDept;
import cn.zq.backstage.dao.UserDeptMapper;
import cn.zq.backstage.service.UserDeptService;
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
 * @since 2021-05-23
 */
@Service
public class UserDeptServiceImpl implements UserDeptService {
    @Autowired
    UserDeptMapper userDeptMapper;
    @Override
    public List<UserDept> getList() {
        return userDeptMapper.getList();
    }
}
