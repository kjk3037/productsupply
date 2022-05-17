package cn.zq.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    private String nickname;

    private Integer status;
    private Date createTime;

    private Date updateTime;

    private String salt;
    List<UserRole> roles;
    List<UserPermission> permissions;
}