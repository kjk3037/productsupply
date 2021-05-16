package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class Dept {
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

}