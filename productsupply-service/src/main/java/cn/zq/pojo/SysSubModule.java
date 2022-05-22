package cn.zq.pojo;

import lombok.Data;

@Data
public class SysSubModule {
    private Integer id;

    private String name;

    private Integer parentId;

    private String path;

    private String parentPath;
}