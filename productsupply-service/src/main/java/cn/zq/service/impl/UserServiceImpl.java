package cn.zq.service.impl;


import cn.zq.dao.UserMapper;
import cn.zq.domain.User;
import cn.zq.domain.UserPermission;
import cn.zq.domain.UserRole;
import cn.zq.service.UserService;
import cn.zq.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Integer moduleId=1;
    @Autowired
    UserMapper userMapper;


    //登录方法
    @Override
    public User login(User user) {
        User account = userMapper.findByUsername(user.getUsername());
//        if(account!=null){
//            User byUsername = userAccountMapper.findByUsername(account.getUsername());
//            account.setRoles(byUsername.getRoles());
//            List<UserPermission> permissionList=new ArrayList<>();
//            if(account.getRoles()!=null){
//                for (UserRole role:account.getRoles()){
//                    if (role.getPermissions()!=null){
//                        for (UserPermission permission:role.getPermissions()){
//                            permissionList.add(permission);
//                        }
//                    }
//                }
//            }
//            account.setPermissions(permissionList);
//        }
        return account;
    }

    @Override
    public List getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public int addUser(User user) {
        user.setId(FormatUtils.uuidFormat());
        return userMapper.insertSelective(user);
    }
    public User findByUsername(String username){
        User byUsername = userMapper.findByUsername(username);
        //account.setRoles(byUsername.getRoles());
        List<UserPermission> permissionList=new ArrayList<>();
        if(byUsername.getRoles()!=null){
            for (UserRole role:byUsername.getRoles()){
                if (role.getPermissions()!=null){
                    for (UserPermission permission:role.getPermissions()){
                        permissionList.add(permission);
                    }
                }
            }
        }
        byUsername.setPermissions(permissionList);
        return byUsername;
    }
}
