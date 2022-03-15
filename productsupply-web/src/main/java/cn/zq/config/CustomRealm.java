package cn.zq.config;

import cn.zq.domain.UserAccount;
//import cn.zq.domain.UserPermission;
import cn.zq.domain.UserPermission;
import cn.zq.domain.UserRole;
import cn.zq.service.UserAccountService;
import cn.zq.utils.MyByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserAccountService userAccountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法被调用");
        UserAccount user = (UserAccount) SecurityUtils.getSubject().getPrincipal();
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
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userName);
        userAccount.setPassword(userPwd);
        UserAccount login = userAccountService.login(userAccount);
        String password = login.getPassword();
        String username=login.getUsername();
        if (username == null) {
            throw new AccountException("用户名不正确");
        }
        return new SimpleAuthenticationInfo(login, password, new MyByteSource(login.getSalt()),getName());
    }


}
