package com.xin.demo;

import com.sun.javafx.binding.SelectBinding;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import  org.apache.ibatis.jdbc.SelectBuilder;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Mybatis的动态 sql
 * @author ly
 * @date 2019/4/18
 */
public class Dynamicsql {
    public static void main(String[] args) throws IOException, SQLException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        动态生成sql
        SelectBuilder.BEGIN();
        SelectBuilder.SELECT("*");
        SelectBuilder.FROM("user");
        SelectBuilder.WHERE("usercode='a'");

        String sql = SelectBuilder.SQL();
        SqlRunner run = new SqlRunner(session.getConnection());
        List<Map<String,Object>> maps = run.selectAll(sql);
        System.out.println(maps);
        System.out.println(sql);
        session.close();
    }


}
