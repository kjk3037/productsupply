package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class Role {
    private String id;

    private String name;

    private String authority;

    private String desc;
}