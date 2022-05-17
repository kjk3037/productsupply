package cn.zq.service;

import cn.zq.pojo.PurchaseOrderDetail;

import java.util.List;

public interface PurchaseOrderDetailService {
    PurchaseOrderDetail selectByPrimaryKey(String id);
    List getList();
}
