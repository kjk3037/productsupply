package cn.zq.service.impl;

import cn.zq.pojo.MaterialRequirementOutput;
import cn.zq.dao.MaterialRequirementOutputMapper;
import cn.zq.service.MaterialRequirementOutputService;
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
 * @since 2022-06-12
 */
@Service
public class MaterialRequirementOutputServiceImpl extends ServiceImpl<MaterialRequirementOutputMapper, MaterialRequirementOutput> implements MaterialRequirementOutputService {
    @Autowired
    MaterialRequirementOutputMapper requirementOutputMapper;
    @Override
    public List getList() {
        return requirementOutputMapper.getList();
    }
}
