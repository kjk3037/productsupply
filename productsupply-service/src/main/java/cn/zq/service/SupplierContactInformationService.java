package cn.zq.service;

import cn.zq.pojo.SupplierContactInformation;

import java.util.List;

public interface SupplierContactInformationService {
    SupplierContactInformation selectByPrimaryKey(Integer id);
    List getList();
}
