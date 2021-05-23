package cn.zq.backstage.service;

import cn.zq.backstage.domain.District;

import java.util.List;

public interface DistrictService {
    District selectByPrimaryKey(Integer id);
    List getList();
}
