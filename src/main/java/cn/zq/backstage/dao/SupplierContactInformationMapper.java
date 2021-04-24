package cn.zq.backstage.dao;

import cn.zq.backstage.domain.SupplierContactInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupplierContactInformationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierContactInformation record);

    int insertSelective(SupplierContactInformation record);

    SupplierContactInformation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierContactInformation record);

    int updateByPrimaryKey(SupplierContactInformation record);
}