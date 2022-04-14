package cn.zq.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FormatUtils {
    public static String SHA1="sha1";
    public static String MD5="md5";
    public static int Iteration=512;
    public static String uuidFormat(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String codeFormat(String prev,Integer id){
        return new StringBuffer(prev+"-").append(timeFormat(new Date())).append("00"+id).toString();
    }
    public static String timeFormat(Date date){
        return new SimpleDateFormat("YYYYMMDD").format(date);
    }
    public static String encodeMD5(String password,String salt){
        return new SimpleHash(MD5,password,salt,Iteration).toString();
    }
    public static String encodeSHA1(String password,String salt){
        return new SimpleHash(SHA1,password,salt,Iteration).toString();
    }
    @Test
    public void generMD5P(){

        System.out.println(encodeMD5("123","3b29896210744b3ca370bc8a84934425"));
    }
}
