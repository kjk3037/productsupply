package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.UserRoleMapper;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.UserRole;
import cn.zq.backstage.service.UserRoleService;
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
