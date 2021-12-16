package cn.zq.service.impl;

import cn.zq.dao.PurchaseOrderMapper;
import cn.zq.domain.PurchaseOrder;
import cn.zq.service.PurchaseOrderService;
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
