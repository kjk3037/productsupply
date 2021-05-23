package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.AuthorityMapper;
import cn.zq.backstage.domain.Authority;
import cn.zq.backstage.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityMapper authorityMapper;
    @Override
    public Authority selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
