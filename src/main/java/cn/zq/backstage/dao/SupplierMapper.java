package cn.zq.backstage.dao;

import cn.zq.backstage.domain.Supplier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupplierMapper {
    int deleteByPrimaryKey(String id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}