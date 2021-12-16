package cn.zq.service.impl;

import cn.zq.dao.SysSubModuleMapper;
import cn.zq.domain.SysSubModule;
import cn.zq.service.SysSubModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysSubModuleServiceImpl implements SysSubModuleService {
    @Autowired
    SysSubModuleMapper sysSubModuleMapper;
    @Override
    public SysSubModuleService getSubModule() {
        return null;
    }

    @Override
    public SysSubModule selectByPrimaryKey(Integer id) {
        return null;
    }
}
