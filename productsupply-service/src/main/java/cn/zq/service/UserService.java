package cn.zq.service;

import cn.zq.domain.User;

import java.util.List;

public interface UserService {
    User login(User user);
    List getUsers();
    int addUser(User user);
    User findByUsername(String username);
}
