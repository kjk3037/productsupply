package cn.zq.backstage.service;

import cn.zq.backstage.domain.SysModule;

import java.util.List;

public interface SysModuleService {
    SysModule selectByPrimaryKey(Integer id);
    List<SysModule> getModule();
}
