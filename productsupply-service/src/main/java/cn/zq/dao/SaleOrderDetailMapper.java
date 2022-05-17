package cn.zq.dao;

import cn.zq.pojo.SaleOrderDetail;
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
public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetail> {
    List<SaleOrderDetail> selectByOrderId(String orderId);
    List<SaleOrderDetail> selectByOrderCode(String orderCode);
    Integer insertBatch(List<SaleOrderDetail> details);
    Integer maxId();
}
