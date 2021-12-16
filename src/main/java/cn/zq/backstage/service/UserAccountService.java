package cn.zq.backstage.service;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.UserAccount;

import java.util.List;

public interface UserAccountService {
    Message login(UserAccount userAccount);
    Message<List> getUsers();
    Message addUser(UserAccount userAccount);
}
