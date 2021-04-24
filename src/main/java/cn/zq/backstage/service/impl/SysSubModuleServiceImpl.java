package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.SysSubModuleMapper;
import cn.zq.backstage.service.SysSubModuleService;
import org.springframework.beans.factory.annotation.Autowired;

public class SysSubModuleServiceImpl implements SysSubModuleService {
    @Autowired
    SysSubModuleMapper sysSubModuleMapper;
    @Override
    public SysSubModuleService getSubModule() {
        return null;
    }
}
