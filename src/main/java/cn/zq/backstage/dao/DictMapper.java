package cn.zq.backstage.dao;

import cn.zq.backstage.domain.Dict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    List getList();
}