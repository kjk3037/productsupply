package cn.zq.dao;

import cn.zq.domain.PurchaseOrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseOrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseOrderDetail record);

    int insertSelective(PurchaseOrderDetail record);

    PurchaseOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseOrderDetail record);

    int updateByPrimaryKey(PurchaseOrderDetail record);

    List getList();
}