package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.PurchaseOrderMapper;
import cn.zq.backstage.domain.PurchaseOrder;
import cn.zq.backstage.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Override
    public PurchaseOrder selectByPrimaryKey(String id) {
        return purchaseOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getList() {
        return null;
    }
}
