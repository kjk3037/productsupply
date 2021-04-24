package cn.zq.backstage.service;

import cn.zq.backstage.domain.Message;
import cn.zq.backstage.domain.User;

public interface UserService {
    Message login(User user);
}
