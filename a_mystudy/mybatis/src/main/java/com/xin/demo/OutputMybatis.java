package com.xin.demo;

import com.xin.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 4、mybatis框架的输出
        所谓的输出，其实就是SQL的操作结果
        4.1 增加、修改、删除

        4.2 查询
        <select id="selectUsers"  resultType="com.xin.bean.User">
        resultType属性表示将查询结果转换为指定的对象
        Mybatis框架面向sql，所以对象和sql没有关系
        将查询结果转换为指定类型的对象时，需要遵循转换规则，将查询结果字段的名称和指定类型的属性名称进行匹配，如果匹配成功，那么反射调用，
        如果匹配不成功，什么都不做，在极限情况下，一个属性都没有有匹配成功，那么对象不会创建，获取null
        如果名称不一致，也希望可以转换成功，那么需要别名，或者采用独立的配置方式<resultMap></resultMap>

        查询结果转换为多种类型
        1、自定义类型 当获取的数据之间存在关系的时候，采用自定义类型
        2、Map类型：将查询结果转换为map集合类型
                    将查询结果字段名作为key，将查询结果值作为val，形成键值对对象，放置在map中
        当获取数据之间没有任何的关系，采用map（select sum(score),avg(sum）from user）
        3、简单类型:将查询结果的第一个字段值返回 当获取数量或者单一字段值的时候，使用简单类型，获取数据
 * @author ly
 * @date 2019/4/17
 */
public class OutputMybatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("|------------------自定义类型------------------------|");
//       自定义类型
        List<Emp> emps= session.selectList("selectUser1");
        System.out.println(emps.size());

        for (Emp emp:emps) {
            System.out.println(emp.getEno()+","+emp.getEname());
        }
        System.out.println("|------------------Map类型:------------------------|");

//        Map类型:将查询结果字段名作为key，将查询结果值作为val，形成键值对对象，放置在map中
        Map map = session.selectOne("selectUserByCode","a");
        System.out.println(map);

        System.out.println("|-----------------简单类型-------------------------|");

        //将查询结果转化为简单类型，将查询结果的第一个字段值返回
        String s = session.selectOne("selectUserSimple","a");
        System.out.println(s);
        session.close();
    }
}

