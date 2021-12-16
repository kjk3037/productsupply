package cn.zq.service.impl;

import cn.zq.dao.SupplierAccountMapper;
import cn.zq.domain.SupplierAccount;
import cn.zq.service.SupplierAccountService;
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
