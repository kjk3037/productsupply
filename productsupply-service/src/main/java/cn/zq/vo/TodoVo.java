package cn.zq.vo;

import lombok.Data;

import java.util.Map;

/*
* 代办信息对象
* */
@Data
public class TodoVo {
    private String path;
    private Map info;
    private String activityName;
    private String processName;
}
