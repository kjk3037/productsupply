package cn.zq.service;

import cn.zq.pojo.FieldStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-05-29
 */
public interface FieldStatusService extends IService<FieldStatus> {
    Map getMapByKey(String procDefKey,String taskDefKey);
}
