package cn.zq.service;

import cn.zq.domain.SysSubModule;

public interface SysSubModuleService {
    SysSubModuleService getSubModule();
    SysSubModule selectByPrimaryKey(Integer id);
}
