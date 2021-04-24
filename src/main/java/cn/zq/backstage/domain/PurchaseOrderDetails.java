package cn.zq.backstage.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class PurchaseOrderDetails {
    private String id;

    private String orderId;

    private Integer quantity;

    private String name;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer quantityReceived;

    private String materialNumber;

    private Date updateDate;
}