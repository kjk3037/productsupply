package cn.zq.backstage.service.impl;

import cn.zq.backstage.dao.DistrictMapper;
import cn.zq.backstage.domain.District;
import cn.zq.backstage.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Override
    public District selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
