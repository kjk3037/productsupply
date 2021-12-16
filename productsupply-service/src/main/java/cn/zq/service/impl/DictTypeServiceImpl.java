package cn.zq.service.impl;

import cn.zq.dao.DictTypeMapper;
import cn.zq.domain.DictType;
import cn.zq.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    DictTypeMapper dictTypeMapper;
    @Override
    public DictType selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
