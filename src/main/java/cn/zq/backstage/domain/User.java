package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private String nickname;

    private Integer status;
}