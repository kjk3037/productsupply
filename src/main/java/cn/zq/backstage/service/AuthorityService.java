package cn.zq.backstage.service;

import cn.zq.backstage.domain.Authority;

import java.util.List;

public interface AuthorityService {
    Authority selectByPrimaryKey(Integer id);
    List getList();
}
