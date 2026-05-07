# Spring MVC

Spring MVC 是 Spring 全家桶的一款产品，对 Java Web 开发流程进行了封装，不需要再通过 Servlet 的方式来完成代码的开发，无论是代码量还是参数的接收，返回值等等各方面都做了优化。

Spring MVC：基于 Spring 框架的，即基于 IoC 容器。

- Servlet: 创建一个 Servlet 类，接收客户端请求，完成相关的业务操作。但每个业务操作对应一个 Servlet。
- Spring MVC：也是基于 Servlet 类， 但请求进入到一个总的 Servlet，由这个 Servlet 再将这些请求分发到不同的 Java 类中，由各自的 Java 类处理具体的业务。

## 前置控制器（总的 Servlet 类）
在src/main/webapp/WEB-INF/web.xml定义，映射所有的请求<url-pattern>/</url-pattern>，由DispatcherServlet进行请求的分发。

## Tomcat启动
1. 首先进入web.xml，配置读取springmvc.xml
2. 然后进入springmvc.xml，配置扫描com.mvc.controller包下的所有类
3. 扫描带有特定注解的类注入到IoC容器中，@Controller才能接收请求，@Component只是注入

配置tomcat
- Tomcat Server， Local
- deployment 选择 + 号，Artifact，修改根路径

## Spring MVC返回
函数返回：网页（前后端不分离的单体架构） 或 数据（前后端分离）

1. 视图，定义方法返回值类型为 String，返回视图名称，同时结合视图解析器，将真正的视图资源返回给调用者。
2. 数据 JSON，需要在方法定义处添加 @RequestBody，方法中直接返回数据即可。

ResponseBody：直接返回数据，只能接收字符串

```java
@RequestMapping("/test")
@ResponseBody
public String test() {
    System.out.println("test...");
    return "test";
}
```

视图解析器：将返回值（视图名称）映射到真正的目标资源。web的根目录是webapp，在springmvc.xml配置

```java
@RequestMapping("/index")
public String index() {
    System.out.println("index...");
    return "index";
}
```

## Spring MVC传参

Spring MVC 会自动对 request 中的参数进行解析，类型转换，开发者只需要在方法定义处声明需要的参数类型格式，Spring MVC 会自动对参数进行解析和转换。

