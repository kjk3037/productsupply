package cn.zq.service;

import cn.zq.domain.SaleOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
public interface SaleOrderService extends IService<SaleOrder> {
    public String createOrder(SaleOrder order);
    void confirmOrder(String orderCode,String comment);
    void finishOrder(String orderCode,String comment,Integer status);
    Integer updateOrderStatus(String orderCode,Integer status);
    Integer updateOrder(SaleOrder saleOrder);
}
