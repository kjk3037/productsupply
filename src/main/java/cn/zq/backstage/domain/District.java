package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class District {
    private Integer id;

    private Integer pid;

    private String districtName;

    private Integer type;

    private Integer hierarchy;

    private String districtSqe;

}