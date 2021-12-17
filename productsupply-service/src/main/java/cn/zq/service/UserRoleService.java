package cn.zq.service;

import cn.zq.common.Message;
import cn.zq.domain.UserRole;

import java.util.List;

public interface UserRoleService {
    List getRoles();
    UserRole selectByPrimaryKey(Integer id);
    List getList();
}
