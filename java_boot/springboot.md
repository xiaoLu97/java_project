# 依赖

springboot 2.x区别

- spring-boot-starter-web，非默认的spring-boot-starter-webmvc
- lombok要给版本号
- 可以没有NewsMapper.xml
- 支持中文

# 业务配置

在控制台注入mapper，需要在Application类上添加@MapperScan，指定mapper接口的包路径
```java
@Autowired
private NewsMapper newsMapper;
```

# 笔记 表单案例

## springboot区别

SSM 框架整合完成之后，需要进行大量的配置，Spring IoC 来完成，完成 Spring MVC 和 MyBatis 所需对象的创建

Java Web 启动机制，通过反射进行创建 DispatcherServlet、ContextLoaderListener，web.xml

IoC 创建 dataSource、sqlSessionFactory、MapperScannerConfigurer、InternalResourceViewResolver、Mapper 动态代理对象

Java 是面向对象的编程语言

手动配置 bean 步骤繁琐、出错率高，如何优化？

Spring Boot 框架应运而生

Spring 手动配置 web.xml、spring.xml、config.xml、springmvc.xml

Spring Boot 不需要进行任何通用配置，不需要任何的 XML 文件，自动配置

Spring MVC、MyBatis 的基础组件，个性化的配置仍然需要

Spring Boot 自动装配

基于 Spring Boot，在此基础上添加各种第三方框架 Spring MVC、MyBatis、MyBatis Plus、Spring Security、Spring Data JPA

Spring + Spring MVC + MyBatis

Spring Boot + Spring MVC + MyBatis

## Thymeleaf 前端模板，HTML

**不能直接通过 url 来访问 templates 路径下的资源**，Thymeleaf 模板必须经过 Controller 才能加载

url -》Controller -》Thymeleaf
```
<a href="/add.html">添加</a> // 错误
```

语法
```html
<h1 th:text="${name}"></h1>

<h1 th:if="${score>=90}">优秀</h1>
<h1 th:if="${score<90 && score>80}">良好</h1>

<tr th:each="news:${list}">
    <td th:text="${news.id}"></td>
    <td th:text="${news.title}"></td>
</tr>

<input type="text" name="title" th:value="${news.title}"/>
```

## application 配置文件

Spring Boot 工程的配置文件名称必须是 application，格式有两种，分别是 properties 和 yaml，如果同时存在两个格式的配置文件，优先级更高的是 properties。

properties
```properties
server.port=8181
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/jdbc_test
spring.datasource.username=root
spring.datasource.password=root
```

# 笔记 自动装配

## Spring Boot

Spring 和 Spring Boot 都是用来构建 Java 工程的脚手架

Spring 和 Spring Boot 的区别？

Spring 需要手动进行组件的配置

Spring Boot 是自动进行组件的配置

## Spring Boot 如何自动进行组件的配置

自动装配

引入了 MyBatis 的依赖，项目启动时会自动加载 MyBatis 相关的组件

DataSource、sqlSessionFactory...

当启动 Spring Boot 的时候，pom.xml 中配置的所有框架的组件都会自动进行加载

如何进行自动装配

1、pom.xml 中添加 MyBatis 的依赖

2、Spring Boot 就会自动加载 MyBatis 需要的 bean

配置类：Spring 框架提供的一种特殊的类，专门用来配置（创建）各种 bean（对象）

只需要在该配置类中将需要创建的对象进行配置即可，Spring 框架就会读取配置类，进而创建这些对象（反射）

Spring Boot 如何自动实现 bean 的创建，通过配置类的方式

引入 MyBatis 依赖之后，依赖中会包含一个配置类的信息，配置类中会标注要创建的 bean

Spring Boot 启动之后会读取配置类信息，从而获取到 MyBatis 需要创建的 bean，然后进行创建

文件路径：META-INF/spring.factories

```yacas
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration,\
org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
```

key:org.springframework.boot.autoconfigure.EnableAutoConfiguration

value:org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration

org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration

Spring  Boot 启动的时候，会读取 spring.factories 文件，通过 key 获取到两个配置类

1、MybatisLanguageDriverAutoConfiguration

2、MybatisAutoConfiguration

# MyBatis Plus

实际开发中会使用 MyBatis Plus 来替代 MyBatis

MyBatis Plus 是基于 MyBatis 进行了更加细致的封装，进一步减少代码量

MyBatis 需要开发者手动定义 SQL，MyBatis Plus 可以自动生成 SQL 语句，不需要开发者手动编写

MyBatis Plus 的依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.3.1</version>
</dependency>
```

MyBatis Plus 除了可以自动生成 SQL 之外，还可以自动生成代码（Controller、Service、Mapper、Entity）

MyBatis Plus 逆向工程的依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.2</version>
</dependency>

<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.7</version>
</dependency>
```

# Spring Boot 整合 JdbcTemplate

Spring 自带的 JDBC 模板组件，底层实现了对 JDBC 的封装，需要开发者自定义 SQL 语句，JdbcTemplate 帮助我们完成数据库的连接，SQL 的执行，结果集的封装。

JdbcTemplate 提供了通用的 SQL 操作方法，execute、update、batchUpdate、query

JdbcTemplate 和 MyBatis 的区别？

MyBatis 的 SQL 定义在 XML 文件中，JdbcTemplate 的 SQL 定义在 Java 类中

```java
package com.southwind.controller;

import com.southwind.entity.News;
import com.southwind.jdbctemplate.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsMapper newsMapper;

    @GetMapping("/list")
    public List<News> list(){
        return this.newsMapper.list();
    }

    @GetMapping("/getById/{id}")
    public News getById(@PathVariable("id") Integer id){
        News news = null;
        try {
            news = this.newsMapper.getById(id);
        } catch (Exception e) {
            return null;
        }
        return news;
    }

    @PostMapping("/add")
    public void add(@RequestBody News news){
        this.newsMapper.add(news);
    }

    @PutMapping("/update")
    public void update(@RequestBody News news){
        this.newsMapper.update(news);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        this.newsMapper.deleteById(id);
    }

    @GetMapping("/batchAdd")
    public void batchAdd(){
        this.newsMapper.batchAdd();
    }

    @GetMapping("/batchUpdate")
    public void batchUpdate(){
        this.newsMapper.batchUpdate();
    }

    @GetMapping("/batchDelete")
    public void batchDelete(){
        this.newsMapper.batchDelete();
    }

}
```

# Spring Boot 整合 Spring Data JPA

Spring Data JPA 是 Spring 框架提供的持久层解决方案

实体类

```java
package com.southwind.jpaentity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Date createtime;
    @Column
    private String opername;
}
```

# Spring Boot 整合 Spring Security

Spring Security、Shiro 安全框架，对请求进行校验。


## Spring Security

安装了依赖后，内置了一个登录页面

除了最基本的登录认证，还可以使用 Spring Security 来完成资源权限管理：当请求某个资源时，对角色进行验证，如果该角色拥有访问权限则正常访问，否则无法访问。

角色：ADMIN、USER

ADMIN 可以访问 welcome.html 和 admin.html

USER 只能访问 welcome.html，否则403 Forbidden

### 遇到问题：

不打开控制台能正确跳回/admin，打开就不正常跳到/.well-known/appspecific/com.chrome.devtools.json

当 Chrome DevTools 处于打开状态，并且您正在浏览从 localhost 提供的网站时，它会自动向以下地址发送请求  GET /.well-known/appspecific/com.chrome.devtools.json

在 Spring Security 配置里放行这个路径

## CSRF（Cross-Site Request Forgery，跨站请求伪造）

是一种常见的 Web 安全攻击方式。
- 用户登录了你的银行网站 bank.com，浏览器保存了会话 Cookie
- 用户没有退出登录，又访问了恶意网站 evil.com
- 恶意网站上有一个隐藏的表单或图片链接：
- 浏览器会自动带上 bank.com 的 Cookie 发送请求
- 银行网站以为是用户自己在操作，执行了转账
- 核心问题： 浏览器会自动携带 Cookie，服务器无法区分这个请求是用户主动发起的，还是被恶意网站伪造的。