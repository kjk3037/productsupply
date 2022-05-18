package cn.zq.service;

import cn.zq.pojo.ActRuIdentitylink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-05-18
 */
public interface ActRuIdentitylinkService extends IService<ActRuIdentitylink> {
    List getByUsername(String username);
}
