package cn.zq.dao;

import cn.zq.domain.UserAuthority;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthority record);

    int insertSelective(UserAuthority record);

    UserAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuthority record);

    int updateByPrimaryKey(UserAuthority record);

    List getList();
}