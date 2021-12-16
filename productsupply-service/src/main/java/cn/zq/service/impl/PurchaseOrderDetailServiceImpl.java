package cn.zq.service.impl;

import cn.zq.dao.PurchaseOrderDetailMapper;
import cn.zq.domain.PurchaseOrderDetail;
import cn.zq.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService {
    @Autowired
    PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Override
    public PurchaseOrderDetail selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
