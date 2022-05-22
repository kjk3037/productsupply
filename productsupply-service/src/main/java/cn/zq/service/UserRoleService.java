package cn.zq.service;

import cn.zq.pojo.UserRole;

import java.util.List;

public interface UserRoleService {
    List getRoles();
    UserRole selectByPrimaryKey(Integer id);
    List getList();
    List getByUsername(String username);
    UserRole getByName(String name);
}
