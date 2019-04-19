package com.xin.Spring.demo;

import com.xin.Spring.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ly
 * @date 2019/4/19
 */
public class SpringTest {
    public static void  main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-user.xml");
        User user  = context.getBean("user", User.class);
//        System.out.println(user.getUsername()+","+user.getDept().getDeptname());
        System.out.println(user.getUsername()+","+user.getDeptname());
    }
}
