package com.xin.demo;

import com.xin.bean.User;
import com.xin.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *2、 namespace
 * <p>
 * Mybatis：主要用于区分不同映射文件中的相同名称的SQL映射id
        1、同一个映射文件中无法使用命名空间区分相同名称sql映射id
        2、如果不同的映射文件中存在了相同名称的sql映射id，可以使用命名空间加以区分，
            使用方式：命名空间.sql映射id
        3、如果能够保证sql映射id不会重复，那么命名空间可以省略

 命名空间访问方式：
        命名空间.sql映射id==》userDao.selectUsers
        将命名空间和接口进行绑定，然后将方法和映射id进行绑定
        当调用接口中的方法时，等同于调用命名空间中的sql映射id
        框架采用JDK动态代理实现接口绑定
 通过命名空间可以打印SQL执行的详细log
        log4j.logger.命名空间的名称=TRACE
 *
 * @author ly
 * @date 2019/4/16
 */
public class NameSpace {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        查询集合数据，方法的参数表示SQL映射id,如果映射id查找不到，那么会发生错误，如果查找到，就可以执行。
//       List<User> list =  session.selectList("user.selectUsers");
//       System.out.println(list.size());

        UserDao dao = session.getMapper(UserDao.class);
        List<User> users = dao.selectUsers();
        System.out.println(users.size());

        session.close();
    }
}
