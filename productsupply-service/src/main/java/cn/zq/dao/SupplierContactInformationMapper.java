package cn.zq.dao;

import cn.zq.domain.SupplierContactInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierContactInformationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierContactInformation record);

    int insertSelective(SupplierContactInformation record);

    SupplierContactInformation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierContactInformation record);

    int updateByPrimaryKey(SupplierContactInformation record);

    List getList();
}