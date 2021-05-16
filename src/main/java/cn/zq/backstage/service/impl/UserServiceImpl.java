package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.UserMapper;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.User;
import cn.zq.backstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Message<User> login(User user) {
        Message<User> userMessage = new Message<>();
        User u=userMapper.loginCheck(user);
        if(!u.equals(null)){
            if(u.getPassword().equals(user.getPassword())){
                userMessage.setCode("200");
                userMessage.setData(u);
                userMessage.setInfo("登录成功");
                return userMessage;
            }
            userMessage.setCode("500");
            userMessage.setInfo("账号或者密码错误");
            return userMessage;
        }
        userMessage.setCode("500");
        userMessage.setInfo("账号不存在");
        return userMessage;
    }

    @Override
    public Message<List> getUsers() {
        Message<List> listMessage = new Message<>();
        listMessage.setData(userMapper.getUsers());
        listMessage.setCode("200");
        listMessage.setInfo("获取成功");
        return listMessage;
    }

}
