package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class Authority {
    private Integer id;

    private String describe;

    private Byte status;

    private String module;

    private Integer roleId;

}