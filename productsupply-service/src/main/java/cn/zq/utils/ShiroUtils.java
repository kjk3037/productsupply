package cn.zq.utils;

import org.apache.shiro.SecurityUtils;

public class ShiroUtils {
    /*
    * 获取当前用户名
    * */
    public static String getUsername(){
        return (String) SecurityUtils.getSubject().getPrincipal();
    }
}
