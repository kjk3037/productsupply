package cn.zq.service;

import cn.zq.pojo.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder selectByPrimaryKey(String id);
    List getList();
}
