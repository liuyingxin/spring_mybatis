package com.xin.demo;

import com.xin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3、Mybatis框架的参数输入 Input
 * 所谓输入就是SQL的参数
 * JDBC
     * 1.1、拼串
         * String id = "1 or 1=1";
         * String sql = "select * from user where id="+id;
          * select * from user where id=1 or q=q
         * 会出现sql注入问题
         * 多次执行相同的sql，但是参数不一样，会增加编译次数，降低效率
     * 2.2、占位符
         * String sql = "select * from user where id=?";
         * ....
         * pstat.setInt(1,1);
         * 不会出现sql注入的问题
         * 多次执行相同的sql，减少编译的次数 ，提高效率
 * <p>
 * Mybatis
    * 占位符（？）
         * 由框架将sql和程序代码分析开，所以无法直接确认参数的含义
         * 所以框架采用特殊的符号来代替占位符
         * #{参数名称}==》？

         * 如果sql中的参数是多个，那么传递参数的类型就需要变化，不能是字符串。
            1、参数类型为简单类型（字符串类型，Integer,int）
                 如果sql中有多个参数，那么所有的取值完全相同，参数名称可以任意，只要遵循标识符的命名规则即可

            *应用场景：sql中只有一个参数的时候，使用简单类型
 *
            2、参数类型可以为自定义类型（User）
                 sql文中的参数会从自定义类型的属性中获取（必须保证名称相同）
                 执行时，会根据参数名称在对象中反射调用方法，如果相同的名称的属性不存在，会发生异常，所以参数名称和属性名称要保持一致

            *应用场景：sql中多个参数有相同的含义，使用自定义类型

            3、参数类型可以为集合类型（Map）
                sql文中的参数会从Map集合中根据key获取参数值
                所以参数形成必须和key的名称保持一致

            *应用场景：sql中多个参数没有任何关系，采用Map集合类型
 *
    * 拼串
     占位符传递参数一般是在条件中使用，其他场合一般使用拼串的方式。
     框架采用特殊的符号进行拼串操作
        ${参数名称}
    拼接时，直接拼接到SQL当中，而不是通过参数传递

 ------------------------------------------------
        如果是条件中使用参数，那么必须采用占位符
        如果在其他场合，可以采用拼串
        如果参数是用户输入的，那么不能拼串
        如果参数是系统生成的，那么可以拼串

 *模糊查询
        sql=select * from user where usercode like '%a%'

        不同的数据库采用不同的方式实现sql的字符串拼接
        mysql：concat(s1,s2,s3)
        oracle：|| == > +

 * @author ly
 * @date 2019/4/16
 */

/**
 * SqlSessionFactory和SqlSession的实现过程：
 *      mybatis框架主要是围绕着SqlSessionFactory进行的，创建过程大概如下：
 *          (1)、定义一个Configuration对象，其中包含数据源、事务、mapper文件资源以及影响数据库行为属性设置settings
 *          (2)、通过配置对象，则可以创建一个SqlSessionFactoryBuilder对象
 *          (3)、通过 SqlSessionFactoryBuilder 获得SqlSessionFactory 的实例。
 *           (4)、SqlSessionFactory 的实例可以获得操作数据的SqlSession实例，通过这个实例对数据库进行
 * 如果是spring和mybaits整合之后的配置文件,一般以这种方式实现,SqlSessionFactory的创建:
 *      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
 *      <property name="dataSource" ref="dataSource"></property>
 *
 *      <property name="mapperLocations" value="classpath:com/cn/mapper/*.xml"></property>
 *      </bean>
 *       SqlSessionFactoryBean是一个工厂Bean，根据配置来创建SqlSessionFactory
 */
public class InputMybatis{
    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

//        SqlSession：应用程序和数据库之间交互的一个单线程对象（非线程安全的），
//                  数据库的C、R、U、D及事务的处理接口，select | update | delete | insert | commit | rollback | close | flushStatements等
//        SqlSessionFactory:工厂设计模式，创建SqlSession的工厂。
//                  SqlSessionFactory是MyBatis的关键对象,它是个单个数据库映射关系经过编译后的内存镜像，
//                  SqlSessionFactory对象的实例可以通过SqlSessionFactoryBuilder对象类获得,
//                  而SqlSessionFactoryBuilder则可以从XML配置文件或一个预先定制的Configuration的实例构建出SqlSessionFactory的实例.（单例线程安全）

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        System.out.println("|----------查询数据，传递参数,类型为简单类型-----------------------|");
//       查询数据，传递参数,类型为简单类型
        User  user = session.selectOne("user.selectUsers","a");
        System.out.println(user.getUsername());

        System.out.println("|----------查询数据，传递参数,类型为自定义类型-----------------------|");
//        查询数据，传递参数,类型为自定义类型
        User paramUser = new User();
        paramUser.setUsercode("d");
        paramUser.setUserpswd("1234");
        User user1 = session.selectOne("selectUser",paramUser);
        System.out.println(user1.getUsername());

        System.out.println("|----------查询数据，传递参数,类型为Map集合类型-----------------------|");
//        查询数据，传递参数,类型为Map集合类型
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("usercode","a");
        paramMap.put("userpswd","123");
        User user2 = session.selectOne("selectUser",paramMap);
        System.out.println(user2.getUsername());

        System.out.println("|----------查询不同表的数据-----------------------|");
//        查询不同表的数据
        Map<String,Object> paramMap1 = new HashMap<String,Object>();
        paramMap1.put("tableName","customers");
        List<User> userList = session.selectList("selectTableName",paramMap1);
        System.out.println(userList.size());

        System.out.println("|----------查询某一列的数据-----------------------|");

//        //   查询某一列的数据
        Map<String,Object> paramMap3 = new HashMap<String,Object>();
        paramMap3.put("colName","usercode");
        List<User> userList3 = session.selectList("selectColName",paramMap3);
        System.out.println(userList3.size());

        //   排序
        Map<String,Object> paramMap4 = new HashMap<String,Object>();
        paramMap4.put("orderType","asc");
        List<User> userList4 = session.selectList("selectOrder",paramMap4);
        System.out.println(userList4.size());

        System.out.println("|----------  模糊查询-----------------------|");
//        模糊查询
        Map<String,Object> paramMap2 = new HashMap<String,Object>();
//        paramMap.put("usercode","a");// select * from user where usercode like '%${usercode}%'
//        paramMap.put("usercode","%a%");// select * from user where usercode like #{usercode}
        paramMap2.put("usercode","a");
        List<User> userList1 = session.selectList("selectLikeName",paramMap2);
        System.out.println(userList1.size());



        session.close();


    }
}
