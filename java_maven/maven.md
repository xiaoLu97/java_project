# Maven
Maven 帮助工程进行 jar 包管理以及父子工程构建的服务

jar 包管理：Maven 有一个远程仓库，包含了所有的 jar，本地只需要连接到远程仓库，就可以自动将相应的 jar 包下载到本地，再自动导入到工程中
- 由 Maven 自动给程序导入 jar
- pom.xml 中配置工程所需的 jar

## 安装
https://maven.apache.org/download.cgi 搜索Maven 3 archives

安装后需要配置：
- MAVEN_HOME → Maven 安装目录
- PATH → 添加 %MAVEN_HOME%\bin

本地仓库 C:\Users\01432743\.m2\repository

配置镜像：Maven 安装目录的 conf/ 目录下
```
<mirror>
    <id>alimaven</id>
    <mirrorOf>central</mirrorOf>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
</mirror>
```

设置idea：File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven -> Maven home directory -> 配置maven安装目录 -> Maven user settings -> 配置maven安装目录下的conf/settings.xml

## 插件LomBok
lombok 自动创建 get、set、toString 等方法的工具

## 创建工程

项目模板 Archetype 选择: maven-archetype-webapp, 等待构建完成显示 BUILD SUCCESS

# Spring

Spring 不是业务层框架，它是构建业务层框架的框架

Spring 提供的是底层的容器，该容器来构建业务层框架

业务框架(springMVC、Mybatis)本质上也是由各个对象组成的，Spring 提供的容器(IoC)就是管理这些对象的

spring的两大核心组件**IoC:** + **AOP**

## IoC 控制反转

将对象的创建方式进行反转，由原来的手动 new 变成现在的 Spring 框架自动创建

IoC 创建对象，只需要在配置文件中设置你要创建的对象即可

1. 在 XML 文件中配置 Bean （spring.xml）
2. 通过注解进行配置
   - 实体类必须添加注解
   - 构建 IoC 的时候包必须覆盖实体类
   - 一个类只能构造一个 bean （缺点）
3. 基于配置类，创建一个配置类替代xml文件

注意：@component和@Bean都是创建Bean对象到IoC容器，

所以方法三和方法二可以互通（前提：只能通过包的方式构造，AnnotationConfigApplicationContext的入参）

## AOP 面向切面编程

AOP 是对 OOP 的一种补充，是在另外一个纬度上抽象出对象，具体是指程序运行时动态将非业务切入到业务代码中，实现了业务代码和非业务代码的解耦合。

非业务代码：日志

IOC容器会把业务对象(CalImpl)和切面对象(LoggerAspect)通过注解(@EnableAspectJAutoProxy)生成动态代理对象