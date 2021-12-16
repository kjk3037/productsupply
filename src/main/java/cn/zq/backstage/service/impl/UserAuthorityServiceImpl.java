package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.UserAuthorityMapper;
import cn.zq.backstage.domain.UserAuthority;
import cn.zq.backstage.service.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {
    @Autowired
    UserAuthorityMapper userAuthorityMapper;
    @Override
    public UserAuthority selectByPrimaryKey(Integer id) {
        return userAuthorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getList() {
        return userAuthorityMapper.getList();
    }
}
