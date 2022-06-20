package cn.zq.service.impl;

import cn.zq.pojo.Dict;
import cn.zq.dao.DictMapper;
import cn.zq.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-06-16
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    DictMapper dictMapper;

    @Override
    public List<Dict> getByType(String typeName) {
        return dictMapper.selectByType(typeName);
    }
}
