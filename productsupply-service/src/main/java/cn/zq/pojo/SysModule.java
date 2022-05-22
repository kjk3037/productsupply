package cn.zq.pojo;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
@Data
public class SysModule {
    private Integer id;

    private String name;

    private String path;
    private String icon;
    private List<SysSubModule> child=new LinkedList();

    @Override
    public String toString() {
        return "SysModule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", child=" + child +
                '}';
    }
}