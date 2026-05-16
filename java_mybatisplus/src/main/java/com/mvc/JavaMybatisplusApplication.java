package com.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mvc.mapper")
public class JavaMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMybatisplusApplication.class, args);
    }

}
