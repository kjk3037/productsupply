package cn.zq.backstage.service;

import cn.zq.backstage.domain.SupplierContactInformation;

import java.util.List;

public interface SupplierContactInformationService {
    SupplierContactInformation selectByPrimaryKey(Integer id);
    List getList();
}
