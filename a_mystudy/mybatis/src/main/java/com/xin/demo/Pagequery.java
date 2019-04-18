package com.xin.demo;

import com.xin.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5、Mybatis的分页查询
        5.1 分页查询，由于实现方式不一样，可分为两种逻辑分页和物理分页
             *------逻辑分页------
            一次性将数据库的所有数据查询出来，放置在缓存当中。
            通过逻辑算法获取制定页码的数据，这种方式称之为逻辑分页
            查询效率快（内存操作），但是影响其他软件的使用，降低服务器性能

           *------物理分页------查询效率低（进程操作），不影响服务器性能
            推荐：采用数据库本身的分页机制实现分页查询操作，称之为物理分页


        mysql：
            limit startIndex，pagesize
            limit pagesize

        oracle:
            三层查询嵌套+rownum
            rownum不支持大于和大于等于操作，不能和order by语句联合使用
            select * from (select t.*,rownum as num from(select * from user order by regdate) t
            where rownum <=10) where num >5

 * @author ly
 * @date 2019/4/18
 */
public class Pagequery {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        int pageno = 1;
        int pageSize = 5;
//        //物理分页
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startIndex",(pageno-1)*pageSize);
        map.put("pageSize",pageSize);
        List<User> userList = session.selectList("selectMyUser",map);
        for (User user:userList) {
            System.out.println(user.getUsername());
        }

        //逻辑分页
//        RowBounds rb = new RowBounds((pageno-1)*pageSize,pageSize);
//        List<User> userList = session.selectList("selectMyAllUser",null,rb);
//        for (User user:userList) {
//            System.out.println(user.getUsername());
//        }
        session.close();

    }

}
