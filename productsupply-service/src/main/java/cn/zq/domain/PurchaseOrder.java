package cn.zq.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class PurchaseOrder {
    private String id;

    private String title;

    private String purchaseContent;

    private Long orderAmount;

    private Integer supplierId;

    private Integer orderNumber;

    private Date orderDate;

    private BigDecimal outStandingAmount;

    private String remark;

    private Integer processStatus;

    private String purchaseReason;

    private String otherPurchaseReasonRemark;

    private Integer goodsType;

    private String orderRemark;

    private BigDecimal amountPaid;

    private Integer warehousingStatus;

    private Integer invoiceType;

    private Integer isInvoice;

    private Integer invoiceStatus;

    private String jdPurchaseOrderNumber;

    private Integer paymentMethod;

    private String otherPaymentMethodReason;

    private Integer fullPaymentStatus;

    private Integer allMaterialQuantity;

    private Date createTime;

    private Date updateTime;

}