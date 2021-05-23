package cn.zq.backstage.service;

import cn.zq.backstage.domain.Supplier;
import cn.zq.backstage.domain.SupplierAccount;

import java.util.List;

public interface SupplierAccountService {
    SupplierAccount selectByPrimaryKey(Integer id);
    List getList();
}
