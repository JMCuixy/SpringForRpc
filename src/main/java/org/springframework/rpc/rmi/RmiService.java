package org.springframework.rpc.rmi;


import org.springframework.rpc.model.User;

import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
public interface RmiService {
    void print();
    List<User> listInfo();
}
