package cn.zq.vo;

import cn.zq.pojo.SaleOrder;
import lombok.Data;

@Data
public class DataVo<T> {
    private T data;
    private String activityName;
}
