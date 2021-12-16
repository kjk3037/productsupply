package cn.zq.service;

import cn.zq.domain.Message;
import cn.zq.domain.UserAccount;

import java.util.List;

public interface UserAccountService {
    Message login(UserAccount userAccount);
    Message<List> getUsers();
    Message addUser(UserAccount userAccount);
}
