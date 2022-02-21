package cn.zq.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

public class FormatUtils {
    public static String SHA1="sha1";
    public static String MD5="md5";
    public static int Iteration=512;
    public static String uuidFormat(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String encodeMD5(String password,String salt){
        return new SimpleHash(MD5,password,salt,Iteration).toString();
    }
    public static String encodeSHA1(String password,String salt){
        return new SimpleHash(SHA1,password,salt,Iteration).toString();
    }
}
