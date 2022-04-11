package cn.zq.shiro;

import cn.zq.domain.User;
//import cn.zq.domain.UserPermission;
import cn.zq.domain.UserPermission;
import cn.zq.domain.UserRole;
import cn.zq.service.UserService;
import cn.zq.utils.MyByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    /*
    * 授权方法
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("redis未命中，使用数据库执行授权");
        User user = (User) userService.findByUsername(SecurityUtils.getSubject().getPrincipal().toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        Set<String> roleSet = new HashSet<>();
        if(user.getPermissions()!=null){
            for (UserPermission permission:user.getPermissions()){
                stringSet.add(permission.getPermission());
            }
            info.setStringPermissions(stringSet);
        }
        if (user.getRoles()!=null){
            for (UserRole role:user.getRoles()){
                roleSet.add(role.getName());
            }
            info.setRoles(roleSet);
        }
        return info;
    }

    /**
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("-------身份认证方法--------");
        System.out.println("redis未命中，进入数据库执行认证");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        User user = new User();
        user.setUsername(userName);
        user.setPassword(userPwd);
        User login = userService.login(user);
        String password = login.getPassword();
        String username=login.getUsername();
        if (username == null) {
            throw new AccountException("用户名不正确");
        }
        return new SimpleAuthenticationInfo(login.getUsername(), password, new MyByteSource(login.getSalt()),getName());
    }


}
