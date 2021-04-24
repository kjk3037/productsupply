package cn.zq.backstage.domain;

import lombok.Data;

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
}