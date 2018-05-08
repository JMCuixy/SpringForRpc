package org.springframework.rpc.model;

import java.io.Serializable;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */

public class User implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 1：男 0：女
     */
    private Integer sex;


    public User(String userName, String password, Integer sex) {
        this.userName = userName;
        this.password = password;
        this.sex = sex;
    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
