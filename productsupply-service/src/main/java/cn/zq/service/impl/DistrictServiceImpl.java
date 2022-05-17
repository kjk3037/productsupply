package cn.zq.service.impl;

import cn.zq.dao.DistrictMapper;
import cn.zq.pojo.District;
import cn.zq.service.DistrictService;
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
