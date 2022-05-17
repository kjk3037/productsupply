package cn.zq.service;

import cn.zq.pojo.User;

import java.util.List;

public interface UserService {
    User login(User user);
    List getUsers();
    int addUser(User user);
    User findByUsername(String username);
}
