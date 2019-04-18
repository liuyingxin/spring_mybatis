package com.xin.demo;

import com.xin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6、Mybatis框架的动态参数查询
 *
 *      6.1动态参数查询表示，查询条件不是固定不变的，由用户来决定
 *        框架采用特殊的标签进行参数条件的判断
 *        <if></if>判断条件是否成立，如果成立，将标签体中SQL和外部的SQL拼接到一起
 *        <where></where>框架根据条件自动判断是否增加where关键字，如果第一个条件成立时，以and开头，and会自动省略
 *        <choose><when test=""></when><otherwise></otherwise></choose>
 * @author ly
 * @date 2019/4/18
 */
public class Dynamicquery {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();


        //where if 标签
//        Map<String,Object> map = new HashMap<String,Object>();
////        map.put("usercode","a");
//        map.put("userpswd","123");
//
//        List<User> userList = session.selectList("selectDynamicUser",map);
//        for (User user:userList) {
//            System.out.println(user.getUsername());
//        }


        //foreach 标签
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("aaa");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("usercodes",list);
        List<User> userList = session.selectList("selectUserInRange",map);
        for (User user:userList) {
            System.out.println(user.getUsername());
        }
        session.close();
    }

}
