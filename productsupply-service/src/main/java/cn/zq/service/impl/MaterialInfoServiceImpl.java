package cn.zq.service.impl;

import cn.zq.pojo.MaterialInfo;
import cn.zq.dao.MaterialInfoMapper;
import cn.zq.service.MaterialInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2022-04-13
 */
@Service
public class MaterialInfoServiceImpl extends ServiceImpl<MaterialInfoMapper, MaterialInfo> implements MaterialInfoService {
    private static final String KEY = "code";
    @Autowired
    MaterialInfoMapper materialInfoMapper;
    @Override
    public List getAllMaterial() {
        return materialInfoMapper.getAll();
    }

    @Override
    public MaterialInfo getByCode(String code) {
        QueryWrapper<MaterialInfo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("code",code).last("limit 1");
        return materialInfoMapper.selectOne(objectQueryWrapper);
    }

    @Override
    public MaterialInfo getByKey(String key) {
        QueryWrapper<MaterialInfo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq(KEY,key);
        return materialInfoMapper.selectOne(objectQueryWrapper);
    }

    @Override
    public List getListMaterial(List codes) {
        QueryWrapper<MaterialInfo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.in(KEY,codes);
        return materialInfoMapper.selectList(objectQueryWrapper);
    }

}
