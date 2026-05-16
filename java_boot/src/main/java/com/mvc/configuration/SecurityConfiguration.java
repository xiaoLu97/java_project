package com.mvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/.well-known/appspecific/com.chrome.devtools.json").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/welcome").access("hasRole('ADMIN') or hasRole('USER')")
                .anyRequest().authenticated() // 其他所有请求都需要认证(登录)
                .and() // 返回到上一级配置对象(HttpSecurity),用于连接不同的配置模块
                .formLogin() // 启用表单登录功能
                .permitAll()// 登录页面和登录接口允许所有人访问
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new PasswordEncoderImpl())
                .withUser("user").password(new PasswordEncoderImpl()
                        .encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(new PasswordEncoderImpl()
                        .encode("123456")).roles("ADMIN","USER");
    }
}
