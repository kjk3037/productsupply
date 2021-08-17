package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.UserMapper;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.User;
import cn.zq.backstage.service.UserService;
import cn.zq.backstage.service.WorkflewEntityService;
import cn.zq.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Integer moduleId=1;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WorkflewEntityService workflewEntityService;
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

    @Override
    public Message addUser(User user) {
        user.setId(FormatUtils.uuidFormat());
        userMapper.insertSelective(user);
        Message<User> message=new Message<>();
        message.setCode("200");
        message.setInfo("申请成功");
        return message;
    }

}
