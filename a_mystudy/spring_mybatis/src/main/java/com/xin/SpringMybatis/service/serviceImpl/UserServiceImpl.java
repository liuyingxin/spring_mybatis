package com.xin.SpringMybatis.service.serviceImpl;

import com.xin.SpringMybatis.bean.User;
import com.xin.SpringMybatis.dao.UserDao;
import com.xin.SpringMybatis.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author ly
 * @date 2019/4/19
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public User getUserById(Integer id) {
        return  userDao.getUserById(id);
    }
}
