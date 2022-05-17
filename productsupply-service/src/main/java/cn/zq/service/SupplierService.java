package cn.zq.service;

import cn.zq.pojo.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier selectByPrimaryKey(Integer id);
    List getList();
}
