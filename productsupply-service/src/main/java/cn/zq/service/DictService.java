package cn.zq.service;

import cn.zq.pojo.Dict;

import java.util.List;

public interface DictService {
    Dict selectByPrimaryKey(Integer id);
    List getList();
}
