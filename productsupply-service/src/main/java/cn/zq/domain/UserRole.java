package cn.zq.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private Integer deptId;
    private Date createTime;

    private Date updateTime;

}