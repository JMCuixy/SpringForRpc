package org.springframework.rpc.service;


import org.springframework.rpc.model.User;

import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
public interface UserService {
    List<User> listInfo();
}
