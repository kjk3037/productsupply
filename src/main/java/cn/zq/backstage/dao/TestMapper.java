package cn.zq.backstage.dao;

import cn.zq.backstage.domain.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
