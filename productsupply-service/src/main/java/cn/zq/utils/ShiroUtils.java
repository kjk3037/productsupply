package cn.zq.utils;

import cn.zq.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroUtils {
    /*
    * 获取当前用户名
    * */

    public static String getUsername(){

        return (String) SecurityUtils.getSubject().getPrincipal();
    }
}
