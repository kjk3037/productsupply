package cn.zq.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserRole implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private Integer deptId;
    private Date createTime;

    private Date updateTime;
    private List<UserPermission> permissions;

}