package cn.zq.backstage.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierContactInformation {
    private String id;

    private String title;

    private String supplierId;

    private String name;

    private Integer phoneNumber;

    private Integer telephoneNumber;

    private String position;

    private String email;

    private String shopLink;
    private Date createTime;

    private Date updateTime;
}