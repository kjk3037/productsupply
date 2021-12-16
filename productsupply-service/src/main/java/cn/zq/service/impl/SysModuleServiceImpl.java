package cn.zq.service.impl;

import cn.zq.dao.SysModuleMapper;
import cn.zq.dao.SysSubModuleMapper;
import cn.zq.domain.SysModule;
import cn.zq.domain.SysSubModule;
import cn.zq.service.SysModuleService;
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
