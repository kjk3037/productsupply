package cn.zq.backstage.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Supplier {
    private String id;

    private String title;

    private String supplierNumber;

    private String supplierName;

    private String supplierLevel;

    private String invoiceTitle;

    private String supplierDesc;

    private String supplierTaxesNumber;

    private Byte isBlackList;

    private Byte isCommoditySupplier;

    private Byte isOutsourcingProcessors;

    private String supplierAddress;

    private String position;
    private Date createTime;

    private Date updateTime;

}