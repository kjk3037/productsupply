package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.SupplierMapper;
import cn.zq.backstage.domain.Supplier;
import cn.zq.backstage.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierMapper supplierMapper;
    @Override
    public Supplier selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
