package cn.zq.service.impl;

import cn.zq.domain.UserDept;
import cn.zq.dao.UserDeptMapper;
import cn.zq.service.UserDeptService;
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
