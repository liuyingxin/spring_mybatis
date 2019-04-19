package com.xin.SpringMybatis.bean;

/**
 * @author ly
 * @date 2019/4/19
 */
public class User {
    private Integer id;
    private String usercode;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
