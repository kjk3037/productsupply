package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.SupplierAccountMapper;
import cn.zq.backstage.domain.SupplierAccount;
import cn.zq.backstage.service.SupplierAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierAccountServiceImpl implements SupplierAccountService {
    @Autowired
    SupplierAccountMapper supplierAccountMapper;
    @Override
    public SupplierAccount selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
