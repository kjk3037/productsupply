package cn.zq.service.impl;

import cn.zq.dao.UserAccountMapper;
import cn.zq.common.Message;
import cn.zq.domain.UserAccount;
import cn.zq.domain.UserPermission;
import cn.zq.domain.UserRole;
import cn.zq.service.UserAccountService;
import cn.zq.service.WorkflewEntityService;
import cn.zq.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private Integer moduleId=1;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    WorkflewEntityService workflewEntityService;

    //登录方法
    @Override
    public UserAccount login(UserAccount userAccount) {
        UserAccount account = userAccountMapper.loginCheck(userAccount);
        if(account!=null){
            UserAccount byUsername = userAccountMapper.findByUsername(account.getUsername());
            account.setRoles(byUsername.getRoles());
            List<UserPermission> permissionList=new ArrayList<>();
            if(account.getRoles()!=null){
                for (UserRole role:account.getRoles()){
                    if (role.getPermissions()!=null){
                        for (UserPermission permission:role.getPermissions()){
                            permissionList.add(permission);
                        }
                    }
                }
            }
            account.setPermissions(permissionList);
        }
        return account;
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
    public UserAccount findByUsername(String username){
        return userAccountMapper.findByUsername(username);
    }
}
