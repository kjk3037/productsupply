package cn.zq.service.impl;

import cn.zq.dao.UserAccountMapper;
import cn.zq.common.Message;
import cn.zq.domain.UserAccount;
import cn.zq.service.UserAccountService;
import cn.zq.service.WorkflewEntityService;
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
    public UserAccount login(UserAccount userAccount) {
        //旧编码
        //Message<UserAccount> userMessage = new Message<>();
//        if(!u.equals(null)){
//            if(u.getPassword().equals(userAccount.getPassword())){
//                userMessage.setCode(200);
//                userMessage.setData(u);
//                userMessage.setInfo("登录成功");
//                return userMessage;
//            }
//            userMessage.setCode(500);
//            userMessage.setInfo("账号或者密码错误");
//            return userMessage;
//        }
//        userMessage.setCode(500);
//        userMessage.setInfo("账号不存在");
        return userAccountMapper.loginCheck(userAccount);
    }

    @Override
    public List getUsers() {
        return userAccountMapper.getUsers();
    }

    @Override
    public int addUser(UserAccount userAccount) {
        userAccount.setId(FormatUtils.uuidFormat());
        return userAccountMapper.insertSelective(userAccount);
    }

}
