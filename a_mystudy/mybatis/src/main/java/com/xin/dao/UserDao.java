package com.xin.dao;

import com.xin.bean.User;

import java.util.List;

/**
 * @author ly
 * @date 2019/4/16
 */
public interface UserDao {
    public List<User> selectUsers();
}
