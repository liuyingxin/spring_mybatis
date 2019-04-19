package com.xin.SpringMybatis.dao;

import com.xin.SpringMybatis.bean.User;

/**
 * @author ly
 * @date 2019/4/19
 */

public interface UserDao {
    User getUserById(Integer id);
}
