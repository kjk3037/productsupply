package cn.zq.backstage.dao;

import cn.zq.backstage.domain.SupplierAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupplierAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierAccount record);

    int insertSelective(SupplierAccount record);

    SupplierAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierAccount record);

    int updateByPrimaryKey(SupplierAccount record);
}