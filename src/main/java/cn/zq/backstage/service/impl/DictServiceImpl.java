package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.DictMapper;
import cn.zq.backstage.domain.Dict;
import cn.zq.backstage.service.DictService;
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
