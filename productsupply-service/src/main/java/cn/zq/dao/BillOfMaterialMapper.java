package cn.zq.dao;

import cn.zq.pojo.BillOfMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-06-08
 */
public interface BillOfMaterialMapper extends BaseMapper<BillOfMaterial> {
    List selectByCode(String code);
}
