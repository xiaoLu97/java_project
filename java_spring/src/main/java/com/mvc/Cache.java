package com.mvc;

import com.mvc.entity.News;
import com.mvc.entity.Supplier;
import com.mvc.mapper.SupplierMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Cache {
    public static void main(String[] args) {
        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);

        Supplier supplier = new Supplier();
//        supplier.setId(1);
        supplier.setUsername("张三");
//        supplier.setPassword("123456"); // 静态sql缺少一个条件都查不出来
        supplier.setAge(22);

        Supplier supplier1 = mapper.get(supplier);
        System.out.println(supplier1);
    }
}
