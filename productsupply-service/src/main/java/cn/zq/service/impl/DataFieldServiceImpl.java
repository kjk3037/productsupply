package cn.zq.service.impl;

import cn.zq.domain.DataField;
import cn.zq.dao.DataFieldMapper;
import cn.zq.service.DataFieldService;
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
 * @since 2022-04-19
 */
@Service
public class DataFieldServiceImpl extends ServiceImpl<DataFieldMapper, DataField> implements DataFieldService {
    @Autowired
    DataFieldMapper dataFieldMapper;

    public List getChilds(String parentKey) {
        QueryWrapper<DataField> objectQueryWrapper = new QueryWrapper<DataField>();
        objectQueryWrapper.eq("parent_key",parentKey);
        return dataFieldMapper.selectList(objectQueryWrapper);
    }
    /*
    * 精简视图字段
    * */
    public List getByBussinessKey(String bussinessKey) {
        return dataFieldMapper.getByBussinessKey(bussinessKey);
    }
    /*
    * 详细视图字段
    * */
    public List getByBussinessKeyAll(String bussinessKey) {
        return dataFieldMapper.getByBussinessKeyAll(bussinessKey);
    }
}
