package cn.zq.backstage.service;

import cn.zq.backstage.domain.DictType;

import java.util.List;

public interface DictTypeService {
    DictType selectByPrimaryKey(Integer id);
    List getList();
}
