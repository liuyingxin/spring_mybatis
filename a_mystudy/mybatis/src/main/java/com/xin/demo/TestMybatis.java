package com.xin.demo;


import com.xin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *1、 搭建Mybatis框架的环境以及演示第一个例子
    1、配置jar包
    2、增加配置文件
     主配置文件：数据库的连接信息，映射文件路径
     SQL映射文件：将执行的SQL进行关联映射
 * @author ly
 * @date 2019/4/16
 */
public class TestMybatis {
    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

//      * SqlSessionFactoryBuilder：这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
//              因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）
//       * SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例
//              因此 SqlSessionFactory 的最佳作用域是应用作用域

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//     *SqlSession
//    每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，
//    因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。

        SqlSession session = sqlSessionFactory.openSession();

//        查询集合数据，方法的参数表示SQL映射id,如果映射id查找不到，那么会发生错误，如果查找到，就可以执行。
//       List<User> list =  session.selectList("selectUsers");
//       System.out.println(list.size());

//        查询单一数据
//        查询方法应该和SQL进行对应
//        User user = session.selectOne("selectUser");
//        System.out.println(user.getUsername());

//        查询Map集合数据，将制定的字段作为key，将查询结果作为val，形成键值对象，组合成map集合返回
//        Map<String, Object> map = session.selectMap("selectUsers", "usercode");
//        User user = (User) map.get("c");
//        System.out.println(user.getUsername());

//        查询数据，传递参数
        User user = session.selectOne("selectUser","b");
        System.out.println(user.getUsername());

        session.close();


    }
}
