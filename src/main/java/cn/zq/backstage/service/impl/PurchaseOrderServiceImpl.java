package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.PurchaseOrderMapper;
import cn.zq.backstage.domain.PurchaseOrderDetails;
import cn.zq.backstage.service.PurchaseOrderDetailsService;
import cn.zq.backstage.service.PurchaseOrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderDetailsService {
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Override
    public PurchaseOrderDetails selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
