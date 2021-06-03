package cn.zq.backstage.service.impl;

import cn.zq.backstage.domain.Test;
import cn.zq.backstage.dao.TestMapper;
import cn.zq.backstage.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
