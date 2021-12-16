package cn.zq.service.impl;

import cn.zq.dao.DictMapper;
import cn.zq.domain.Dict;
import cn.zq.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictMapper dictMapper;
    @Override
    public Dict selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
