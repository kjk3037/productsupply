package cn.zq.service;

import cn.zq.domain.Message;
import cn.zq.domain.UserRole;

import java.util.List;

public interface UserRoleService {
    Message getRoles();
    UserRole selectByPrimaryKey(Integer id);
    List getList();
}
