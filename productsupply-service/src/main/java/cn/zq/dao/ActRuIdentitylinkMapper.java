package cn.zq.dao;

import cn.zq.pojo.ActRuIdentitylink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-05-13
 */

public interface ActRuIdentitylinkMapper extends BaseMapper<ActRuIdentitylink> {
    List selectByUser(String userId);
}
