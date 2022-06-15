package cn.zq.service;

import cn.zq.pojo.MaterialRequirementOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-06-12
 */
public interface MaterialRequirementOutputService extends IService<MaterialRequirementOutput> {
    List getList();
}
