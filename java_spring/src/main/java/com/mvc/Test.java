package com.mvc;

import com.mvc.entity.News;
import com.mvc.mapper.NewsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // 通过config构建，getClassLoader类加载器
        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 构建mybatis环境
        SqlSessionFactory sqlSessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper的代理对象。接口与xml结合，得到动态代理对象，操作数据库
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);

        // 新增
        /*News news = new News();
        news.setTitle("测试");
        news.setContent("测试测试测试");
        news.setCreatetime(new Date());
        news.setOpername("admin");

        int add = mapper.add(news);
        System.out.println(add);
        // 提交事务
        sqlSession.commit();*/

        // 查询
        News news = mapper.getById(1);
        System.out.println(news);

        // idea配置问题，只能读取resources下的文件, 获取不到NewsMapper.xml。在maven中配置，但要补充默认的资源配置
    }
}
