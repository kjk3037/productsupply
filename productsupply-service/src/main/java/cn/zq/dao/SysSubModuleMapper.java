package cn.zq.dao;

import cn.zq.domain.SysSubModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysSubModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSubModule record);

    int insertSelective(SysSubModule record);

    SysSubModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSubModule record);

    int updateByPrimaryKey(SysSubModule record);

    List<SysSubModule> getSubModules();
}