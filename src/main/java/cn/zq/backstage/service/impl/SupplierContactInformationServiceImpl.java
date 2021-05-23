package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.SupplierContactInformationMapper;
import cn.zq.backstage.domain.SupplierContactInformation;
import cn.zq.backstage.service.SupplierContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierContactInformationServiceImpl implements SupplierContactInformationService {
    @Autowired
    SupplierContactInformationMapper supplierContactInformationMapper;
    @Override
    public SupplierContactInformation selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
