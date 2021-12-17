package cn.zq.service.impl;

import cn.zq.dao.UserRoleMapper;
import cn.zq.common.Message;
import cn.zq.domain.UserRole;
import cn.zq.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public List getRoles() {
        return  userRoleMapper.getRoles();
    }

    @Override
    public UserRole selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
