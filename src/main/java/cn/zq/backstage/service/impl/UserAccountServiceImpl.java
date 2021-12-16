package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.UserAccountMapper;
import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.UserAccount;
import cn.zq.backstage.service.UserAccountService;
import cn.zq.backstage.service.WorkflewEntityService;
import cn.zq.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private Integer moduleId=1;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    WorkflewEntityService workflewEntityService;
    @Override
    public Message<UserAccount> login(UserAccount userAccount) {
        Message<UserAccount> userMessage = new Message<>();
        UserAccount u= userAccountMapper.loginCheck(userAccount);
        if(!u.equals(null)){
            if(u.getPassword().equals(userAccount.getPassword())){
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
        listMessage.setData(userAccountMapper.getUsers());
        listMessage.setCode("200");
        listMessage.setInfo("获取成功");
        return listMessage;
    }

    @Override
    public Message addUser(UserAccount userAccount) {
        userAccount.setId(FormatUtils.uuidFormat());
        userAccountMapper.insertSelective(userAccount);
        Message<UserAccount> message=new Message<>();
        message.setCode("200");
        message.setInfo("申请成功");
        return message;
    }

}
