package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class SupplierAccount {
    private String id;

    private String title;

    private String supplierId;

    private String bankAccountName;

    private String bankAccount;

    private String depositbank;

    private Byte duplicateremove;
}