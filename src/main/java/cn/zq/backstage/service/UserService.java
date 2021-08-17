package cn.zq.backstage.service;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.User;

import java.util.List;

public interface UserService {
    Message login(User user);
    Message<List> getUsers();
    Message addUser(User user);
}
