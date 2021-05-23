package cn.zq.backstage.service;

import cn.zq.backstage.domain.PurchaseOrderDetails;

import java.util.List;

public interface PurchaseOrderDetailsService {
    PurchaseOrderDetails selectByPrimaryKey(String id);
    List getList();
}
