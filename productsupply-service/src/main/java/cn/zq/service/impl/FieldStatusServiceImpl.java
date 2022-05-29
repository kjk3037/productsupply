package cn.zq.service.impl;

import cn.zq.pojo.FieldStatus;
import cn.zq.dao.FieldStatusMapper;
import cn.zq.service.FieldStatusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Override
    public Map getMapByKey(String procDefKey, String taskDefKey) {
        QueryWrapper<FieldStatus> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("proc_def_key","saleOrder").eq("task_def_key",taskDefKey);
        List<FieldStatus> list = list(objectQueryWrapper);
        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(FieldStatus::getFieldName, FieldStatus::getStatus));
        return collect;
    }
}
