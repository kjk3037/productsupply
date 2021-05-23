package cn.zq.backstage.service;

import cn.zq.backstage.domain.SysSubModule;

import java.util.List;

public interface SysSubModuleService {
    SysSubModuleService getSubModule();
    SysSubModule selectByPrimaryKey(Integer id);
}
