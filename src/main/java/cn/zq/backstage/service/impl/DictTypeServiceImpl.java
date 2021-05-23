package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.DictMapper;
import cn.zq.backstage.dao.DictTypeMapper;
import cn.zq.backstage.domain.DictType;
import cn.zq.backstage.service.DictTypeService;
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
