package com.xin.demo;

import com.xin.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 7、MyBtis框架的DML
 *      DDL数据库定义语言 create ，drop
 *      DML数据操作语言 insert，update，delete
 *      DQL数据查询语言select
 *
 *
 *      获取自增长主键
 *      mysql；<selectKey keyProperty="id" resultType="int">
 *                   select @@identity as id
 *              </selectKey>
 * @author ly
 * @date 2019/4/18
 */
public class cud {
    public static void main(String[] args) throws IOException {

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
        try {
//        增加数据
            Emp emp = new Emp();
            emp.setEno("3333");
            emp.setEname("333");
            session.insert("insertEmp", emp);

            // 修改数据
            emp.setEno("444");
            emp.setEname("4444");
            session.insert("updateEmp", emp);

//       删除数据
            session.insert("deleteEmp", emp);
            session.commit();
        }finally {
            session.close();
        }

    }
}
