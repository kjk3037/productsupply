package cn.zq.backstage.dao;

import cn.zq.backstage.domain.UserAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccount loginCheck(UserAccount userAccount);

    List<UserAccount> getUsers();
}