package cn.zq.service.impl;

import cn.zq.pojo.FieldStatus;
import cn.zq.dao.FieldStatusMapper;
import cn.zq.service.FieldStatusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-05-29
 */
@Service
public class FieldStatusServiceImpl extends ServiceImpl<FieldStatusMapper, FieldStatus> implements FieldStatusService {
    private static final String prefix="field:status:";
    @Qualifier("redisTemplate")
    @Autowired
    RedisTemplate redisTemplate;
//    @Override
//    public Map getMapByKey(String procDefKey, String taskDefKey) {
//        QueryWrapper<FieldStatus> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("proc_def_key","saleOrder").eq("task_def_key",taskDefKey);
//        List<FieldStatus> list = list(objectQueryWrapper);
//        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(FieldStatus::getFieldName, FieldStatus::getStatus));
//        return collect;
//    }
    @Override
    public Map getMapByKey(String procDefKey, String taskDefKey) {
        Set<String> keys = redisTemplate.keys(prefix + procDefKey + ":" + taskDefKey + ":" + "*");
        HashMap<String, Integer> map = new HashMap<>();
        for (String key:keys){
            String[] split = key.split(":");
            String target="";
            if (split.length==5){
                target=split[split.length-1];
                map.put(target, (Integer) redisTemplate.opsForValue().get(key));
            }else {
                map.put(split[split.length-2]+"_"+split[split.length-1], (Integer) redisTemplate.opsForValue().get(key));
            }

        }
        return map;
    }
}
