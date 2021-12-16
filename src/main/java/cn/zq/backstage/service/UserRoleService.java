package cn.zq.backstage.service;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.UserRole;

import java.util.List;

public interface UserRoleService {
    Message getRoles();
    UserRole selectByPrimaryKey(Integer id);
    List getList();
}
