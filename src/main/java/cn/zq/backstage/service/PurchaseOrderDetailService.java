package cn.zq.backstage.service;

import cn.zq.backstage.domain.PurchaseOrderDetail;

import java.util.List;

public interface PurchaseOrderDetailService {
    PurchaseOrderDetail selectByPrimaryKey(String id);
    List getList();
}
