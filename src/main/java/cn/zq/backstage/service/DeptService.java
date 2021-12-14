package cn.zq.backstage.service;

import java.util.List;

public interface DeptService  {
    Dept selectByPrimaryKey(Integer id);
    List getList();
}
