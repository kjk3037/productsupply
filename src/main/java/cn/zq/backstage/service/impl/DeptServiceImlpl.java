package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.DeptMapper;
import cn.zq.backstage.domain.Dept;
import cn.zq.backstage.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImlpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Override
    public Dept selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
