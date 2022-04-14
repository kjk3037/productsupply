package cn.zq.dao;

import cn.zq.domain.SaleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {
    Integer maxId();
    SaleOrder selectByCode(String code);

}
