package cn.zq.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserAccount  implements Serializable {
    private static long serialVersionUID=1L;
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