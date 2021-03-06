package cn.zq.dao;

import cn.zq.pojo.SaleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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
    Integer insertSaleOrder(SaleOrder saleOrder);
    List selectAll();
}
