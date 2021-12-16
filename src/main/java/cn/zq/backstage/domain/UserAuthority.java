package cn.zq.backstage.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserAuthority {
    private Integer id;

    private String describe;

    private Byte status;

    private String module;

    private Integer roleId;
    private Date createTime;

    private Date updateTime;

}