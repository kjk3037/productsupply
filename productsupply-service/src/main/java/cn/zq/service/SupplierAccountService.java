package cn.zq.service;

import cn.zq.domain.SupplierAccount;

import java.util.List;

public interface SupplierAccountService {
    SupplierAccount selectByPrimaryKey(Integer id);
    List getList();
}
