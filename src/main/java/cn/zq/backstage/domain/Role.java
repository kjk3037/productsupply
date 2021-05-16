package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class Role {
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private Integer deptId;
}