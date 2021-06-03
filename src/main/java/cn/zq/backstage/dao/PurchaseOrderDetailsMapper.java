package cn.zq.backstage.dao;

import cn.zq.backstage.domain.PurchaseOrderDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseOrderDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseOrderDetails record);

    int insertSelective(PurchaseOrderDetails record);

    PurchaseOrderDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseOrderDetails record);

    int updateByPrimaryKey(PurchaseOrderDetails record);

    List getList();
}