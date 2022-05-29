package cn.zq.vo;

import cn.zq.pojo.User;
import lombok.Data;

@Data
public class UserVo  {

    private String username;
    private String id;
    private String nickname;
    public UserVo(User user){
        this.username=user.getUsername();
        this.id=user.getId();
        this.nickname=user.getNickname();
    }
}
