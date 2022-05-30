package cn.zq.utils;

import cn.zq.ServiceApp;
import cn.zq.pojo.FieldStatus;
import cn.zq.pojo.SaleOrder;
import cn.zq.pojo.SaleOrderDetail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

public class RedisUtils {

    public static void generFieldStatus(){
        Class<SaleOrder> saleOrderClass = SaleOrder.class;
        Field[] declaredFields = saleOrderClass.getDeclaredFields();
        String prefix="saleOrder:sid-ea74e1f9-738b-4a42-93e5-052844c63df7:";
        for (Field field :declaredFields){
            String key = new String("field:status:" + prefix + field.getName());
            RedisTemplate redisTemplate = (RedisTemplate) BeanUtils.getBean("redisTemplate");
            redisTemplate.opsForValue().set(key,2);
        }
    }
}
