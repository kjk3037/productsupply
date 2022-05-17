package cn.zq.service;

import cn.zq.pojo.SysSubModule;

public interface SysSubModuleService {
    SysSubModuleService getSubModule();
    SysSubModule selectByPrimaryKey(Integer id);
}
