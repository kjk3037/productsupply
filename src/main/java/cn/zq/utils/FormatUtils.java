package cn.zq.utils;

import java.util.UUID;

public class FormatUtils {
    public static String uuidFormat(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
