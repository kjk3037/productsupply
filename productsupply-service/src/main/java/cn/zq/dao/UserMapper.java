package cn.zq.dao;

import cn.zq.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loginCheck(User user);

    List<User> getUsers();

    User selectByUsername(String username);
    User selectByUserId(String userId);
}