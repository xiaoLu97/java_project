package com.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public Phone phone() {
        Phone phone = new Phone();
        phone.setBrand("Samsung");
        phone.setColor("Black");
        return phone; // 返回的对象注入到容器
    }

}
