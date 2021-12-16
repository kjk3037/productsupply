package cn.zq.service.impl;

import cn.zq.dao.UserRoleMapper;
import cn.zq.domain.Message;
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
    public Message getRoles() {
        Message<List> listMessage = new Message<>();
        List<UserRole> userRoles = userRoleMapper.getRoles();
        listMessage.setCode("200");
        listMessage.setData(userRoles);
        listMessage.setInfo("获取角色列表");
        return listMessage;
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
