package cn.zq.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRole {
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private Integer deptId;
    private Date createTime;

    private Date updateTime;
    private List<UserPermission> permissions;

}