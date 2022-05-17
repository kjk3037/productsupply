package cn.zq.service;

import cn.zq.pojo.District;

import java.util.List;

public interface DistrictService {
    District selectByPrimaryKey(Integer id);
    List getList();
}
