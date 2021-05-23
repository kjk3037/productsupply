package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.RoleMapper;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.Role;
import cn.zq.backstage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Message getRoles() {
        Message<List> listMessage = new Message<>();
        List<Role> roles = roleMapper.getRoles();
        listMessage.setCode("200");
        listMessage.setData(roles);
        listMessage.setInfo("获取角色列表");
        return listMessage;
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
