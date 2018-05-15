package org.springframework.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.rpc.model.User;
import org.springframework.rpc.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public List<User> listInfo() {
        User userA = new User("张三", "123456", 1);
        User userB = new User("李四", "123456", 0);
        User userC = new User("王五", "123456", 1);
        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        list.add(userC);
        return list;

    }
}
