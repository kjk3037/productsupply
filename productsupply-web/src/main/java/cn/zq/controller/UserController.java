package cn.zq.controller;

import cn.zq.common.Message;
import cn.zq.domain.User;
import cn.zq.service.UserService;
import cn.zq.utils.FormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;
    /*
    * 登录
    * */
    @PostMapping("/login")
    public Message login(@RequestBody User user){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return Message.failed("未知账户");
        } catch (IncorrectCredentialsException ice) {
            return  Message.failed("密码不正确");
        } catch (LockedAccountException lae) {
            return  Message.failed("账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return  Message.failed("用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return  Message.failed("用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            return  Message.success(subject.getSession(),"登录成功");
        } else {
            token.clear();
            return Message.failed("登录失败");
        }
    }
    @RequiresPermissions("user:query")
    @GetMapping("/getList")
    public Message getUsers(){
        return Message.success(userService.getUsers());
    }
    /*
    * 注册
    * */
    @PostMapping("/register")
    public Message register(@RequestBody User user) {
        User newAccount= user;
        newAccount.setSalt(FormatUtils.uuidFormat());
        newAccount.setPassword(FormatUtils.encodeMD5(newAccount.getPassword(),newAccount.getSalt()));
        int count = userService.addUser(newAccount);
        if (count > 0) {
            return Message.success(count, "注册成功!");
        } else {
            return Message.failed("发生某种错误，注册失败!");
        }
    }
    /*
    * 注销登录状态
    * */
    @PostMapping("/logout")
    public Message logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Message.success("登出成功!");
    }
    @PostMapping("/test")
    public Message test(){
        User account=new User();
        account.setUsername("kjk");
        account.setPassword("1122");
        redisTemplate.opsForValue().set("user:kjk",account);
        User user = (User) redisTemplate.opsForValue().get("user:kjk");
        System.out.println(user);
        return Message.success(user,"success!");
    }
    @GetMapping("/getMy")
    public Message getMy(){
        return Message.success(SecurityUtils.getSubject().getPrincipal());
    }
}
