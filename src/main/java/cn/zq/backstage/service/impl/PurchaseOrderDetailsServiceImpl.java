package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.PurchaseOrderDetailsMapper;
import cn.zq.backstage.domain.PurchaseOrderDetails;
import cn.zq.backstage.service.PurchaseOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderDetailsServiceImpl implements PurchaseOrderDetailsService {
    @Autowired
    PurchaseOrderDetailsMapper purchaseOrderDetailsMapper;
    @Override
    public PurchaseOrderDetails selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
