# SSM 框架整合

SSM：Spring + Spring MVC + MyBatis

Spring Boot 只是替换了 Spring

Spring Boot + Spring MVC + MyBaits

Spring 和 Spring Boot 不是业务框架，只是整合其他业务框架的框架，基础容器

在基础容器上，整合各类业务框架，进行项目的开发

Spring 起了什么作用，IoC 容器自动创建各类对象

InputSteam、SqlSessionFactoryBuilder、SqlSessionFactory、SqlSession

DispatcherServlet、InternalResourceViewResolver

SSM 框架整合本质上就是将 MyBatis 、Spring MVC 所需要的各自对象组件的创建交给 Spring IoC 来完成

Spring 起到的作用就是整合创建另外两个框架所需要的对象

Spring MVC 负责 Web 层的交互，请求响应

MyBatis 负责持久层的交互，连接数据库

Spring Boot 就是优化 Spring

Spring 帮助 MyBatis Spring MVC 创建对象，通过 XML 配置文件的方式来创建对象

在 Spring Boot 中不需要任何的配置文件，自动创建对象，不需要再手动配置对象了

Spring 手动配置对象，Spring Boot 自动配置对象，不需要手动配

## 整合步骤

1. 配置框架各自的配置文件
    - spring.xml spring
    - config.xml mybatis
    - springmvc不用整合
2. 