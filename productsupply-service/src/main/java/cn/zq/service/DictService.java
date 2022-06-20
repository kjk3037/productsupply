package cn.zq.service;

import cn.zq.pojo.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-06-16
 */
public interface DictService extends IService<Dict> {
    List getByType(String typeName);
}
