package cn.zq.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierAccount {
    private String id;

    private String title;

    private String supplierId;

    private String bankAccountName;

    private String bankAccount;

    private String depositbank;

    private Byte duplicateremove;
    private Date createTime;

    private Date updateTime;
}