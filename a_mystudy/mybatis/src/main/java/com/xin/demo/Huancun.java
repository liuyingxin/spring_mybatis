package com.xin.demo;

import com.xin.bean.Emp;
import com.xin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 9、Mybatis的缓存
        一级缓存：sqlsession  默认可以使用
                框架处理缓存是依赖SQL映射的id
                一级缓存无法跨越session，一旦session发生变化，一级缓存失效
        二级缓存：sqlSessioFactroy：默认无法使用
                如果想要使用，需要配置，在映射文件中增加标签<cache></cache>
                要求缓存类可序列化
 * @author ly
 * @date 2019/4/18
 */
public class Huancun {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        //查询数据
       List<Emp> emps = session.selectList("selectEmp");

       for(Emp emp:emps){
           System.out.println(emp.getEname());
       }
       session.close();

       System.out.println("------------*******-------------");

        session = sqlSessionFactory.openSession();
//      emps = session.selectList("selectEmp1");//框架处理缓存是依赖SQL映射的id
        emps = session.selectList("selectEmp");

        for(Emp emp:emps){
            System.out.println(emp.getEname());
        }
        session.close();
    }
}
