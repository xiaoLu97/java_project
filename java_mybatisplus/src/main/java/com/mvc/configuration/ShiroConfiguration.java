package com.mvc.configuration;

import com.mvc.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/*要求：
1、必须是登录状态才能访问 main.html
2、用户必须拥有 manage 权限才能访问 manage.html
3、用户必须拥有 administrator 角色才能访问 aministrator.html*/

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager
    ){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        //权限设置
        Map<String,String> map = new HashMap<>();
        map.put("/auth/main", "authc");
        map.put("/auth/manage", "perms[manage]");
        map.put("/auth/administrator","roles[administrator]");
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("/auth/login");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/auth/unauth");

        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
            @Qualifier("accountRealm") AccountRealm accountRealm
    ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        return securityManager;
    }

    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }

}