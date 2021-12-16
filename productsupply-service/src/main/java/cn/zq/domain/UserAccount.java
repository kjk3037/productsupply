package cn.zq.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserAccount {
    private String id;

    private String username;

    private String password;

    private String nickname;

    private Integer status;
    private Date createTime;

    private Date updateTime;
}