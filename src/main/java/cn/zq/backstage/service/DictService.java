package cn.zq.backstage.service;

import cn.zq.backstage.domain.Dict;

import java.util.List;

public interface DictService {
    Dict selectByPrimaryKey(Integer id);
    List getList();
}
