package cn.zq.service;

import cn.zq.pojo.DictType;

import java.util.List;

public interface DictTypeService {
    DictType selectByPrimaryKey(Integer id);
    List getList();
}
