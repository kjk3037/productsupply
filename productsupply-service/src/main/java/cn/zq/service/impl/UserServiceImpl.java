package cn.zq.service.impl;


import cn.zq.dao.UserMapper;
import cn.zq.pojo.User;
import cn.zq.pojo.UserPermission;
import cn.zq.pojo.UserRole;
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
        User account = userMapper.selectByUsername(user.getUsername());
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
    public User getByUsername(String username){
        User byUsername = userMapper.selectByUsername(username);
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

    @Override
    public User getByUserId(String userId) {
        User byUsername = userMapper.selectByUserId(userId);
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
