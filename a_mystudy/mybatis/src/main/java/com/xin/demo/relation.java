package com.xin.demo;

import com.xin.bean.Classes;
import com.xin.bean.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.io.InputStream;

/**
 * 10、Mybatis框架的多表操作
        表关系
            一对多（班级和学生）
                表设计：在多的一方增加一的一方的外键
                类设计：在一的一方增加多的一方的数据集合引用
            多对一（学生和班级）
                表设计：在多的一方增加一的一方的外键
                类设计：在多的一方增加一的一方的引用
            一对一（用户和身份证）
            多对多（学生和课程）双向的一对多
            自关联（用一个对象体现一对多和多对一）
                表设计 ：id pid
 * @author ly
 * @date 2019/4/18
 */
public class relation {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        //多对一 ：查询学生信息以及所在班级的名称
//        Student s = session.selectOne("selectStudent",3);
//        System.out.println(s.getSname()+","+s.getClasses().getCname());


        //一对多 ：查询班级的名称和所有学生的信息
        Classes c = session.selectOne("selectClasses",2);

        System.out.println(c.getCname());
        System.out.println("--------");

        //* Mybatis的延迟加载
        // *      默认不支持延迟加载功能，如果想要使用，需要弃用延迟加载功能
        // *      <setting name="lazyloadingEnable" value="true"></setting>
        for (Student student:c.getStudents()) {
            System.out.println("---"+student.getSname());
        }


        session.close();

    }
}
