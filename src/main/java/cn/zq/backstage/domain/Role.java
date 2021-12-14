package cn.zq.backstage.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private Integer deptId;
    private Date createTime;

    private Date updateTime;

}