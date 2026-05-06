# Spring MVC

Spring MVC 是 Spring 全家桶的一款产品，对 Java Web 开发流程进行了封装，不需要再通过 Servlet 的方式来完成代码的开发，无论是代码量还是参数的接收，返回值等等各方面都做了优化。

Spring MVC：基于 Spring 框架的，即基于 IoC 容器。

- Servlet: 创建一个 Servlet 类，接收客户端请求，完成相关的业务操作。但每个业务操作对应一个 Servlet。
- Spring MVC：也是基于 Servlet 类， 但请求进入到一个总的 Servlet，由这个 Servlet 再将这些请求分发到不同的 Java 类中，由各自的 Java 类处理具体的业务。

## 前置控制器（总的 Servlet 类）
在src/main/webapp/WEB-INF/web.xml定义，映射所有的请求<url-pattern>/</url-pattern>，由DispatcherServlet进行请求的分发。