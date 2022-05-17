package cn.zq.service.impl;

import cn.zq.dao.SupplierContactInformationMapper;
import cn.zq.pojo.SupplierContactInformation;
import cn.zq.service.SupplierContactInformationService;
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
