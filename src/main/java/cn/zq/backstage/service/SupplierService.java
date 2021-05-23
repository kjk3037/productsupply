package cn.zq.backstage.service;

import cn.zq.backstage.domain.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier selectByPrimaryKey(Integer id);
    List getList();
}
