package com.xin.SpringMybatis.test;

import com.xin.SpringMybatis.bean.User;
import com.xin.SpringMybatis.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ly
 * @date 2019/4/19
 */
public class TestSM {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("mybatis-spring.xml");
         UserService userService =  context.getBean("userService", UserService.class);
         User user = userService.getUserById(3);
         System.out.println(user.getUsername());
    }
}
