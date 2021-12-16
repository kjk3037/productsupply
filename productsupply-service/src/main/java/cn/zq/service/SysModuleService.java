package cn.zq.service;

import cn.zq.domain.SysModule;

import java.util.List;

public interface SysModuleService {
    SysModule selectByPrimaryKey(Integer id);
    List<SysModule> getModule();
}
