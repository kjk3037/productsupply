package cn.zq.backstage.domain;

import lombok.Data;

@Data
public class Message<T> {
    private String code;
    private T data;
    private String info;
}
