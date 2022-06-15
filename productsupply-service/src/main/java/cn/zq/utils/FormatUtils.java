package cn.zq.utils;

import cn.zq.pojo.DataField;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FormatUtils {
    public static String SHA1="sha1";
    public static String MD5="md5";
    public static int Iteration=512;
    public static String uuidFormat(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String codeFormat(String prev,Integer id){
        StringBuffer stringBuffer = new StringBuffer();
        String num="000"+id;
        if (num.length()>4){
            num=num.substring(num.length()-4);
        }
        String date = timeFormat(new Date());
        if (date.length()>8){
            date=date.substring(0,8);
        }
        stringBuffer.append(prev).append("-").append(date).append(num);
        return stringBuffer.toString();
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
//    @Test
//    public void generMD5P() {
//        System.out.println(encodeMD5("123", "3b29896210744b3ca370bc8a84934425"));
//    }
    public static List<DataField> printAllFields(Object o,int type){
        Class cls= o.getClass();
        List<Field> fields=ReflectUtils.getObjectFields(o);
        String name = cls.getName();
        int i = name.lastIndexOf(".");
        String className=name.substring(i+1,i+2).toLowerCase()+ name.substring(i+2);

        ArrayList<DataField> dataFields = new ArrayList<DataField>();
        for (Field field:fields) {
            if(field.getName().equals("serialVersionUID")){
                continue;
            }
            field.setAccessible(true);
            DataField dataField = new DataField();
            dataField.setBussinessKey(className);
            dataField.setProp(field.getName());
            dataField.setFieldType(type);
            dataField.setWidth(150);
            dataFields.add(dataField);
        }
        return dataFields;
    }
}
