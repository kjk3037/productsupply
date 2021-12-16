package cn.zq.service;

import cn.zq.domain.UserAuthority;

import java.util.List;

public interface UserAuthorityService {
    UserAuthority selectByPrimaryKey(Integer id);
    List getList();
}
