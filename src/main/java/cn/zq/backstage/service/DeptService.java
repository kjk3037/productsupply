package cn.zq.backstage.service;

import cn.zq.backstage.domain.Dept;

import java.util.List;

public interface DeptService  {
    Dept selectByPrimaryKey(Integer id);
    List getList();
}
