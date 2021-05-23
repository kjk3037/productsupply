package cn.zq.backstage.service;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.Role;

import java.util.List;

public interface RoleService {
    Message getRoles();
    Role selectByPrimaryKey(Integer id);
    List getList();
}
