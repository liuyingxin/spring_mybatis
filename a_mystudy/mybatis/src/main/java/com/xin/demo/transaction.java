package com.xin.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 8、mybatis的事务处理
        事务传播行为
            多个事务在嵌套使用时的处理方式
        事务的隔离级别
            多个事务并发运行，经常会操作同一个数据来完成他们的任务，并发是必须的，但是会导致脏读、不可重复读、幻读
            脏读（读-改（不提交）-再读(看不到)）
                    一个事务读到了另一个事务更新但是未提交的数据
            不可重复读 （读-改（提交）-再读(不一样)）行级锁
                    在同一个事务中，多次读取同一数据返回的结果有所不同，即后续读取中可以读取到另一个事务已经提交的更新数据
            幻读（统计--插入（提交）-再统计（不准确））表级锁
                    一个事务读取到另一个事务一提交的insert数据

            隔离级别：① Read uncommitted (读未提交)：最低级别，任何情况都无法保证。

 　　                 ② Read committed (读已提交)：可避免脏读的发生。

 　　                 ③ Repeatable read (可重复读)：可避免脏读、不可重复读的发生。 （默认MySQL数据库）

 　　                 ④ Serializable (串行化)：可避免脏读、不可重复读、幻读的发生。

        Mybatis的事务的处理代码
            try{
                .......
                session.commit();
                 }finally{
                session.close();
                 }
 * @author ly
 * @date 2019/4/18
 */
public class transaction {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        session.close();
    }
}
