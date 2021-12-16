package cn.zq.dao;

import cn.zq.domain.UserDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@Mapper
public interface UserDeptMapper extends BaseMapper<UserDept> {
    List<UserDept> getList();
}
