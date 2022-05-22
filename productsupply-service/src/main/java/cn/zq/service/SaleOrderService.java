package cn.zq.service;

import cn.zq.pojo.SaleOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-04-13
 */
public interface SaleOrderService extends IService<SaleOrder> {
    String createOrder(SaleOrder order);
    void confirmOrder(String orderCode,String comment);
    void finishOrder(String orderCode,String comment,Integer status);
    Integer updateOrderStatus(String orderCode,Integer status);
    Integer updateOrder(SaleOrder saleOrder);
    List<SaleOrder> getAll();
    SaleOrder getByKey(String key);

}
