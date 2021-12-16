package cn.zq.backstage.service;

import cn.zq.backstage.domain.UserAuthority;

import java.util.List;

public interface UserAuthorityService {
    UserAuthority selectByPrimaryKey(Integer id);
    List getList();
}
