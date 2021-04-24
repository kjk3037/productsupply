package cn.zq.backstage.dao;

import cn.zq.backstage.domain.SysModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    SysModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);

    List<SysModule> getModules();
}