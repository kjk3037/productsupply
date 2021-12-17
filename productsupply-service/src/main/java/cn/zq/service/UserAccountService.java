package cn.zq.service;

import cn.zq.common.Message;
import cn.zq.domain.UserAccount;

import java.util.List;

public interface UserAccountService {
    UserAccount login(UserAccount userAccount);
    List getUsers();
    int addUser(UserAccount userAccount);
}
