package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.SysModuleMapper;
import cn.zq.backstage.dao.SysSubModuleMapper;
import cn.zq.backstage.domain.SysModule;
import cn.zq.backstage.domain.SysSubModule;
import cn.zq.backstage.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModuleServiceImpl implements SysModuleService {
    @Autowired
    SysModuleMapper sysModuleMapper;
    @Autowired
    SysSubModuleMapper sysSubModuleMapper;

    @Override
    public SysModule selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<SysModule> getModule() {
        List<SysModule> modules = sysModuleMapper.getModules();
        List<SysSubModule> subModules = sysSubModuleMapper.getSubModules();
        for(SysSubModule item:subModules){
            modules.get(item.getParentId()-1).getChild().add(item);
        }
        return modules;
    }
}
