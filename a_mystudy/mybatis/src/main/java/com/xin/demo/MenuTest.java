package com.xin.demo;

import com.xin.bean.Menu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 11
 * @author ly
 * @date 2019/4/18
 */
public class MenuTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        查询菜单树
        List<Menu> menus = session.selectList("selectChidmenus",0);
        for (Menu menu: menus) {
            System.out.println(menu.getMenuname());
        }
        session.close();
    }
}
