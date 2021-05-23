package cn.zq.backstage.service;

import cn.zq.backstage.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder selectByPrimaryKey(String id);
    List getList();
}
